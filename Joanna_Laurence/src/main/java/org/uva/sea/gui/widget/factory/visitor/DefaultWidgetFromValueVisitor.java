package org.uva.sea.gui.widget.factory.visitor;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.gui.widget.CheckBoxWidget;
import org.uva.sea.gui.widget.TextFieldWidget;

public class DefaultWidgetFromValueVisitor implements QuestionModelVisitor<Control> {

    private final FormController controller;

    public DefaultWidgetFromValueVisitor(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control visit(BooleanQuestionModel question) {
        return new CheckBoxWidget(question, this.controller).initialize();
    }

    @Override
    public Control visit(DateQuestionModel question) {
        return new TextFieldWidget(question, this.controller).initialize();
    }

    @Override
    public Control visit(DecimalQuestionModel question) {
        return new TextFieldWidget(question, this.controller).initialize();
    }

    @Override
    public Control visit(ErrorQuestionModel question) {
        return new TextFieldWidget(question, this.controller).initialize();
    }

    @Override
    public Control visit(IntQuestionModel question) {
        return new TextFieldWidget(question, this.controller).initialize();
    }

    @Override
    public Control visit(MoneyQuestionModel question) {
        return new TextFieldWidget(question, this.controller).initialize();
    }

    @Override
    public Control visit(StringQuestionModel question) {
        return new TextFieldWidget(question, this.controller).initialize();
    }
}
