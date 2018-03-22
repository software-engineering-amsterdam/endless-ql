package org.uva.sea.gui.widget.ql;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;
import org.uva.sea.gui.widget.Renderable;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
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
        final CheckBox checkBox = new CheckBox();
        this.applyCheckboxStyle(checkBox, this.questionData.getStyle());
        checkBox.setSelected((this.widgetValue != null) && this.widgetValue.getBooleanValue());
        checkBox.selectedProperty().addListener((observable, oldIsFocused, newIsFocused) ->
        {
            this.sendUpdateValueEvent(checkBox, this.questionData.getQuestionName(), new BooleanValue(newIsFocused));
        });

        return this.createRow(this.questionData.getLabel(), checkBox);
    }

    private void applyCheckboxStyle(CheckBox checkBox, Style style) {
        if (style == null)
            return;

        if ((style.getFont() != null) && (style.getFontSize() != null)) {
            checkBox.setFont(new Font(style.getFont(), style.getFontSize()));
        }
        if (style.getWidth() != null) {
            checkBox.setMinWidth(style.getWidth());
        }else{
            checkBox.setMinWidth(Renderable.TEXT_WIDTH);
        }
    }
}
