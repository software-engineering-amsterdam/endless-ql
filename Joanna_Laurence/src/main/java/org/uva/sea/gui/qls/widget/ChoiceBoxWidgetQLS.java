package org.uva.sea.gui.qls.widget;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class ChoiceBoxWidgetQLS extends WidgetQLS {

    private BooleanValue widgetValue = new BooleanValue(false);

    public ChoiceBoxWidgetQLS(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(BooleanValue booleanValue) {
        this.widgetValue = booleanValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        final ChoiceBox<Boolean> choiceBox = new ChoiceBox<>();
        choiceBox.setValue((this.widgetValue != null) && this.widgetValue.getBooleanValue());

        ObservableList<Boolean> booleanList = FXCollections.observableArrayList(true, false);
        choiceBox.setItems(booleanList);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            BooleanValue newBooleanValue = new BooleanValue(choiceBox.getItems().get((Integer) newValue));
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), newBooleanValue);
        });

        this.setStyle(choiceBox);
        return choiceBox;
    }
}
