package org.uva.sea.gui.widget;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;

public class ChoiceBoxWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: implement DropDown();
        return new ChoiceBox<>();
    }
}
