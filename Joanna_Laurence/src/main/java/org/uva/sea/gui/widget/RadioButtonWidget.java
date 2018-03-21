package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;
import org.uva.sea.gui.newImpl.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class RadioButtonWidget extends Widget {

    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public RadioButtonWidget(BaseQuestionModel questionModel, FormController controller) {
        super(questionModel, controller);
        this.questionModel = questionModel;
        this.controller = controller;
    }

    @Override
    public Control initialize() {
        //TODO: check implementation
        RadioButton radioButton = new RadioButton();

        radioButton = this.createRadioButton(radioButton, this.questionModel.getStyleQLS());

        if (this.questionModel.getValue() != null) {
            System.out.println("Computed boolean value " + this.questionModel.displayValue());
            radioButton.selectedProperty().setValue(new BooleanValue(this.questionModel.displayValue()).getBooleanValue());
        }

        radioButton.selectedProperty().addListener(
                (observable, oldValue, newValue) -> this.controller.updateGuiModel(this.questionModel.getVariableName(), new BooleanValue(newValue))
        );

        return radioButton;
    }

    private RadioButton createRadioButton(RadioButton radioButton, Style style) {
        //TODO: set color
        //TODO: consider what to do with pages and sections
        if (style != null) {
            if ((style.getFont() != null) && (style.getFontSize() != null)) {
                radioButton.setFont(new Font(style.getFont(), style.getFontSize()));
            }
            if (style.getWidth() != null) {
                radioButton.setMinWidth(style.getWidth());
            } else {
                radioButton.setMinWidth(Widget.TEXT_WIDTH);
            }
        }
        return radioButton;
    }
}
