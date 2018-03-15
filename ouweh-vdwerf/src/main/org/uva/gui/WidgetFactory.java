package org.uva.gui;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;
import org.uva.gui.widgets.BooleanWidget;
import org.uva.gui.widgets.IntegerWidget;
import org.uva.gui.widgets.QuestionWidget;
import org.uva.gui.widgets.StringWidget;

public class WidgetFactory {

    private final QuestionChangeListener questionChangeListener;

    public WidgetFactory(QuestionChangeListener questionChangeListener) {
        this.questionChangeListener = questionChangeListener;
    }

    QuestionWidget makeWidget(Question question, Value value, Boolean readOnly) {
        switch (value.getTypeAsString()) {
            case "BOOLEAN":
                return new BooleanWidget(question, value, readOnly, questionChangeListener);
            case "STRING":
                return new StringWidget(question, value, readOnly, questionChangeListener);
            default:
                return new IntegerWidget(question, value, readOnly, questionChangeListener);
        }
    }
}
