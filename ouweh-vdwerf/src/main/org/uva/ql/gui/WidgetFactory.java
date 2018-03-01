package org.uva.ql.gui;

import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.widgets.BooleanWidget;
import org.uva.ql.gui.widgets.IntegerWidget;
import org.uva.ql.gui.widgets.QuestionWidget;
import org.uva.ql.ast.Question;
import org.uva.ql.gui.widgets.StringWidget;

public class WidgetFactory {

    QuestionWidget makeWidget(Question question, Value value){
        switch (value.getTypeAsString()){
            case "BOOLEAN":
                return new BooleanWidget(question, value);
            case "STRING":
                return new StringWidget(question, value);
            default:
                return new IntegerWidget(question, value);
        }
    }
}
