package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;

public class SliderWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: implement Slider
        return new Slider();
    }
}
