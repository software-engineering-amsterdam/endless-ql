package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.factory.visitor.TextFieldFromValueVisitor;

public class TextFieldWidgetFactory extends AbstractWidgetFactory {
    final FormController controller;

    public TextFieldWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return questionModel.accept(new TextFieldFromValueVisitor(this.controller));
    }
}
