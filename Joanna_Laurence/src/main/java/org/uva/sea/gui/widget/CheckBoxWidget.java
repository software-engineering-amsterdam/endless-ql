package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class CheckBoxWidget extends Widget {

    private BooleanValue widgetValue = new BooleanValue(false);

    public CheckBoxWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(BooleanValue booleanValue) {
        this.widgetValue = booleanValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected((this.widgetValue != null) && this.widgetValue.getBooleanValue());
        checkBox.selectedProperty().addListener((observable, oldIsFocused, newIsFocused) ->
                this.sendUpdateValueEvent(this.questionData.getQuestionName(), new BooleanValue(newIsFocused)));

        return checkBox;
    }
}
