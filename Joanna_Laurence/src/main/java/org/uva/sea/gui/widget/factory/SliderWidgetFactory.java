package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.SliderWidget;

public class SliderWidgetFactory extends BaseWidgetFactory {

    private final FormController controller;

    public SliderWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return new SliderWidget(questionModel, this.controller).initialize();
    }
}
