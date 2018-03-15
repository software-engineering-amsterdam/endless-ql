package org.uva.gui;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;
import org.uva.gui.widgets.BooleanWidget;
import org.uva.gui.widgets.IntegerWidget;
import org.uva.gui.widgets.QuestionWidget;
import org.uva.gui.widgets.StringWidget;
import org.uva.qls.ast.Widget.WidgetTypes.*;
import org.uva.qls.evaluator.StyleEvaluator;
import org.uva.qls.visitor.WidgetTypeVisitor;

public class WidgetFactory implements WidgetTypeVisitor<QuestionWidget, Question, Value, Boolean> {

    private final QuestionChangeListener questionChangeListener;
    private final StyleEvaluator styleEvaluator;

    public WidgetFactory(QuestionChangeListener questionChangeListener, StyleEvaluator styleEvaluator) {
        this.questionChangeListener = questionChangeListener;
        this.styleEvaluator = styleEvaluator;
    }

    QuestionWidget makeWidget(Question question, Value value, Boolean readOnly) {
        WidgetType widgetType = styleEvaluator.getWidgetType(question);
        QuestionWidget widget = widgetType.accept(this, question, value, readOnly);
        widget.setQuestionChangeListener(questionChangeListener);
        return widget;
    }

    @Override
    public QuestionWidget visit(CheckboxType checkboxType, Question question, Value value, Boolean readOnly) {
        return new BooleanWidget(question, value, readOnly);
    }

    @Override
    public QuestionWidget visit(DropDownType dropDownType, Question question, Value value, Boolean readOnly) {
        return new BooleanWidget(question, value, readOnly);
    }

    @Override
    public QuestionWidget visit(RadioType radioType, Question question, Value value, Boolean readOnly) {
        return new BooleanWidget(question, value, readOnly);
    }

    @Override
    public QuestionWidget visit(SliderType sliderType, Question question, Value value, Boolean readOnly) {
        return new IntegerWidget(question, value, readOnly);
    }

    @Override
    public QuestionWidget visit(SpinboxType spinboxType, Question question, Value value, Boolean readOnly) {
        return new IntegerWidget(question, value, readOnly);
    }

    @Override
    public QuestionWidget visit(TextType textType, Question question, Value value, Boolean readOnly) {
        return new StringWidget(question, value, readOnly);
    }
}
