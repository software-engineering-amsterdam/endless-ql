package org.uva.sea.gui.widget.qls;

import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class RadioButtonWidget extends Widget {

    private BooleanValue widgetValue = new BooleanValue(false);

    public RadioButtonWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(BooleanValue booleanValue) {
        this.widgetValue = booleanValue;
        return true;
    }

    @Override
    public Control convertToGuiNode() {
        RadioButton radioButton = new RadioButton();
        radioButton = this.createRadioButton(radioButton, this.questionData.getStyle());

        if (this.widgetValue != null) {
            radioButton.selectedProperty().setValue(this.widgetValue.getBooleanValue());
        }

        radioButton.selectedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.sendUpdateValueEvent(this.questionData.getQuestionName(), new BooleanValue(newValue));
                });

        return radioButton;
    }

    private RadioButton createRadioButton(RadioButton radioButton, Style style) {
        if (style == null)
            return radioButton;

        if ((style.getFont() != null) && (style.getFontSize() != null)) {
            radioButton.setFont(new Font(style.getFont(), style.getFontSize()));
        }
        if (style.getWidth() != null) {
            radioButton.setMinWidth(style.getWidth());
        } else {
            radioButton.setMinWidth(Widget.TEXT_WIDTH);
        }

        return radioButton;
    }
}
