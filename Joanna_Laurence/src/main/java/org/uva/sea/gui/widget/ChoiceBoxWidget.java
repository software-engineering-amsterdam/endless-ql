package org.uva.sea.gui.widget;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class ChoiceBoxWidget extends Widget {
    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public ChoiceBoxWidget(BaseQuestionModel questionModel, FormController controller) {
        super(questionModel, controller);
        this.questionModel = questionModel;
        this.controller = controller;
    }

    @Override
    public Control initialize() {
        //TODO: Check choiceBox implementation
        ChoiceBox<Boolean> choiceBox = new ChoiceBox<>();

        choiceBox = this.createChoiceBox(choiceBox, this.questionModel.getStyleQLS());

        if (questionModel.getValue() != null) {
            System.out.println("Computed boolean value " + questionModel.displayValue());
            choiceBox.setValue(new BooleanValue(questionModel.displayValue()).getBooleanValue());
        }

        ObservableList<Boolean> booleanList = FXCollections.observableArrayList(true, false);

        choiceBox.setItems(booleanList);

        ChoiceBox<Boolean> finalChoiceBox = choiceBox;
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            BooleanValue newBooleanValue = new BooleanValue(finalChoiceBox.getItems().get((Integer) newValue));
            System.out.println("Choice selected " + newBooleanValue.getBooleanValue());
            controller.updateGuiModel(questionModel.getVariableName(), newBooleanValue);
        });

        return choiceBox;
    }

    private ChoiceBox createChoiceBox(ChoiceBox<Boolean> choiceBox, Style style) {
        //TODO: set color, font, fontsize
        //TODO: consider what to do with pages and sections
        if (style != null) {
            if (style.getWidth() != null) {
                choiceBox.setMinWidth(style.getWidth());
            } else {
                choiceBox.setMinWidth(Widget.TEXT_WIDTH);
            }

        } else {
            System.out.println("Style is null");
        }

        return choiceBox;
    }
}
