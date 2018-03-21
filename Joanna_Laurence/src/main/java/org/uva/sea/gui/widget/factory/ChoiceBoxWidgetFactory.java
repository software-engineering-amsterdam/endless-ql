package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.ChoiceBoxWidget;

public class ChoiceBoxWidgetFactory extends BaseWidgetFactory {
    final FormController controller;

    public ChoiceBoxWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return new ChoiceBoxWidget(questionModel, this.controller).initialize();
    }
}
