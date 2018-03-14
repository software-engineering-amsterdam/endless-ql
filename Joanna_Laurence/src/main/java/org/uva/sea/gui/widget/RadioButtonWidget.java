package org.uva.sea.gui.widget;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class RadioButtonWidget implements Widget {

    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: check implementation
        RadioButton radioButton = new RadioButton();

        radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                controller.updateGuiModel(questionModel.getVariableName(), new BooleanValue(newValue));
            }
        });

        return radioButton;

    }
}
