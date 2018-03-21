package org.uva.sea.gui.widget.qls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class ChoiceBoxWidget extends Widget {

    private BooleanValue widgetValue = new BooleanValue(false);

    public ChoiceBoxWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(BooleanValue booleanValue) {
        this.widgetValue = booleanValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        ChoiceBox<Boolean> choiceBox = new ChoiceBox<>();

        choiceBox = this.createChoiceBox(choiceBox, this.questionData.getStyle());
        choiceBox.setValue((this.widgetValue != null) && this.widgetValue.getBooleanValue());

        ObservableList<Boolean> booleanList = FXCollections.observableArrayList(true, false);

        choiceBox.setItems(booleanList);

        ChoiceBox<Boolean> finalChoiceBox = choiceBox;
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            BooleanValue newBooleanValue = new BooleanValue(finalChoiceBox.getItems().get((Integer) newValue));
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), newBooleanValue);
        });

        return choiceBox;
    }

    private ChoiceBox createChoiceBox(ChoiceBox<Boolean> choiceBox, Style style) {
        if (style == null)
            return choiceBox;

        //TODO: Create a default style object and cascade the styles
        choiceBox.setMinWidth((style.getWidth() != null) ? style.getWidth() : Widget.TEXT_WIDTH);
        return choiceBox;
    }
}
