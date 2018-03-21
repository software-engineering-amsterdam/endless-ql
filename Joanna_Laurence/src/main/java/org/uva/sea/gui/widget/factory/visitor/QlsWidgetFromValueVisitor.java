package org.uva.sea.gui.widget.factory.visitor;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.gui.widget.Widget;

public class QlsWidgetFromValueVisitor implements QuestionModelVisitor<Control> {

    private final FormController controller;
    private final Widget widget;

    public QlsWidgetFromValueVisitor(FormController controller, Widget widget) {
        this.controller = controller;
        this.widget = widget;
    }

    @Override
    public Control visit(BooleanQuestionModel question) {
        return widget.initialize();
    }

    @Override
    public Control visit(DateQuestionModel question) {
        return widget.initialize();
    }

    @Override
    public Control visit(DecimalQuestionModel question) {
        return widget.initialize();
    }

    @Override
    public Control visit(ErrorQuestionModel question) {
        return widget.initialize();
    }

    @Override
    public Control visit(IntQuestionModel question) {
        return widget.initialize();
    }

    @Override
    public Control visit(MoneyQuestionModel question) {
        return widget.initialize();
    }

    @Override
    public Control visit(StringQuestionModel question) {
        return widget.initialize();
    }
}
