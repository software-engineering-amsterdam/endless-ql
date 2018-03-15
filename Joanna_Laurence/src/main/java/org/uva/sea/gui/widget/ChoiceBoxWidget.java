package org.uva.sea.gui.widget;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

import java.util.ArrayList;

public class ChoiceBoxWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: Check choiceBox implementation
        ChoiceBox<Boolean> choiceBox = new ChoiceBox<>();

        ObservableList<Boolean> booleanList = FXCollections.observableArrayList(true, false);

        choiceBox.setItems(booleanList);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                BooleanValue newBooleanValue = new BooleanValue(choiceBox.getItems().get((Integer) newValue));
                controller.updateGuiModel(questionModel.getVariableName(), newBooleanValue);
            }
        });

        return choiceBox;
    }
}
