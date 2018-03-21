package org.uva.sea.gui.widget.factory.visitor;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.gui.widget.ChoiceBoxWidget;

public class ChoiceBoxFromValueVisitor implements QuestionModelVisitor<Control> {
    private final FormController controller;

    public ChoiceBoxFromValueVisitor(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control visit(BooleanQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }

    @Override
    public Control visit(DateQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }

    @Override
    public Control visit(DecimalQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }

    @Override
    public Control visit(ErrorQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }

    @Override
    public Control visit(IntQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }

    @Override
    public Control visit(MoneyQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }

    @Override
    public Control visit(StringQuestionModel question) {
        return new ChoiceBoxWidget(question, controller).initialize();
    }
}
