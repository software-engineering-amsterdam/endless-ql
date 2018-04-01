package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class RadioButtonWidgetQLS extends WidgetQLS {

    private BooleanValue widgetValue = new BooleanValue(false);

    public RadioButtonWidgetQLS(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(BooleanValue booleanValue) {
        this.widgetValue = booleanValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        RadioButton radioButton = new RadioButton();

        if (this.widgetValue != null) {
            radioButton.selectedProperty().setValue(this.widgetValue.getBooleanValue());
        }

        radioButton.selectedProperty().addListener(
                (observable, oldValue, newValue) -> this.sendUpdateValueEvent(this.questionData.getQuestionName(), new BooleanValue(newValue)));

        this.setStyle(radioButton);
        return radioButton;
    }
}
