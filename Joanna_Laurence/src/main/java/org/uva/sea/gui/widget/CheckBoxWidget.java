package org.uva.sea.gui.widget;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class CheckBoxWidget implements Widget {

    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        CheckBox checkBox = new CheckBox();
        if (questionModel.getValue() != null) {
            System.out.println("Computed boolean value " + questionModel.displayValue());
            checkBox.setSelected(new BooleanValue(questionModel.displayValue()).getBooleanValue());
        }
        checkBox.selectedProperty()
                .addListener((observable, oldIsFocused, newIsFocused) ->
                {
                    controller.updateGuiModel(questionModel.getVariableName(), new BooleanValue(newIsFocused));
                });
        return checkBox;
    }
}
