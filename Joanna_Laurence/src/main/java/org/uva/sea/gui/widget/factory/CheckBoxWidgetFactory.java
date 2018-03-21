package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.CheckBoxWidget;

public class CheckBoxWidgetFactory extends BaseWidgetFactory {
    private final FormController controller;

    public CheckBoxWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return new CheckBoxWidget(questionModel, this.controller).initialize();
    }
}
