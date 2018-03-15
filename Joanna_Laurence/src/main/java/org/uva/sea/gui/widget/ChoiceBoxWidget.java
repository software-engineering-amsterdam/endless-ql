package org.uva.sea.gui.widget;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class ChoiceBoxWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: Check choiceBox implementation
        ChoiceBox<Boolean> choiceBox = new ChoiceBox<>();

        Style style = questionModel.getStyleQLS();

        ObservableList<Boolean> booleanList = FXCollections.observableArrayList(true, false);

        choiceBox.setItems(booleanList);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            BooleanValue newBooleanValue = new BooleanValue(choiceBox.getItems().get((Integer) newValue));
            controller.updateGuiModel(questionModel.getVariableName(), newBooleanValue);
        });

        return choiceBox;
    }
}
