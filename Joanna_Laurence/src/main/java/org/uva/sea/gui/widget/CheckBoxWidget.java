package org.uva.sea.gui.widget;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.text.Font;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class CheckBoxWidget extends Widget {

    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public CheckBoxWidget(BaseQuestionModel questionModel, FormController controller) {
        super(questionModel, controller);
        this.questionModel = questionModel;
        this.controller = controller;
    }

    @Override
    public Control initialize() {
        CheckBox checkBox = new CheckBox();

        checkBox = this.createCheckBox(checkBox, questionModel.getStyleQLS());

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

    private CheckBox createCheckBox(CheckBox checkBox, Style style) {
        //TODO: set color
        //TODO: consider what to do with pages and sections
        if (style != null) {
            if ((style.getFont() != null) && (style.getFontSize() != null)) {
                checkBox.setFont(new Font(style.getFont(), style.getFontSize()));
            }
            if (style.getWidth() != null) {
                checkBox.setMinWidth(style.getWidth());
            }else{
                checkBox.setMinWidth(Widget.TEXT_WIDTH);
            }
        } else {
            System.out.println("Style is null");
        }

        return checkBox;
    }
}
