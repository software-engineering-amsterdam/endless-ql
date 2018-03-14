package qlviz.gui.renderer.javafx;

import qlviz.gui.renderer.layout.QuestionNotFoundException;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.Widget;
import qlviz.model.style.WidgetType;

import java.util.ArrayList;

public class DefaultWidgetProvider implements WidgetFinder, TypedQuestionViewModelVisitor<Widget> {

    @Override
    public Widget findWidgetForQuestion(QuestionViewModel questionViewModel) throws QuestionNotFoundException, WidgetNotFoundException {
        Widget widget = questionViewModel.accept(this);
        if (widget == null) {
            throw new WidgetNotFoundException();
        }
        return widget;
    }

    @Override
    public Widget visit(BooleanQuestionViewModel booleanQuestion) {
        return new Widget(null, WidgetType.Checkbox, new ArrayList<>());
    }

    @Override
    public Widget visit(DateQuestionViewModel dateQuestion) {
        return null;
    }

    @Override
    public Widget visit(DecimalQuestionViewModel decimalQuestion) {
        return new Widget(null, WidgetType.Spinbox, new ArrayList<>());
    }

    @Override
    public Widget visit(IntegerQuestionViewModel integerQuestion) {
        return new Widget(null, WidgetType.Spinbox, new ArrayList<>());
    }

    @Override
    public Widget visit(MoneyQuestionViewModel moneyQuestion) {
        return new Widget(null, WidgetType.Spinbox, new ArrayList<>());
    }

    @Override
    public Widget visit(StringQuestionViewModel stringQuestion) {
        return new Widget(null, WidgetType.Text, new ArrayList<>());
    }
}
