package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;

public class RadioButtonWidget implements Widget {

    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: implement RadioButton
        return new RadioButton();
    }
}
