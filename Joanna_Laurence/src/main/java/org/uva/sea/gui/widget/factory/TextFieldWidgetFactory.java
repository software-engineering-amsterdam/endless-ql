package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.TextFieldWidget;

public class TextFieldWidgetFactory extends BaseWidgetFactory {
    final FormController controller;

    public TextFieldWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return new TextFieldWidget(questionModel, controller).initialize();
    }
}
