package org.uva.sea.gui.widget.factory.visitor;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.gui.widget.SpinnerWidget;

public class SpinnerWidgetFromValueVisitor implements QuestionModelVisitor<Control> {
    private final FormController controller;

    public SpinnerWidgetFromValueVisitor(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control visit(BooleanQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }

    @Override
    public Control visit(DateQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }

    @Override
    public Control visit(DecimalQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }

    @Override
    public Control visit(ErrorQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }

    @Override
    public Control visit(IntQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }

    @Override
    public Control visit(MoneyQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }

    @Override
    public Control visit(StringQuestionModel question) {
        return new SpinnerWidget(question, controller).initialize();
    }
}
