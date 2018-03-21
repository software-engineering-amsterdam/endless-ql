package org.uva.sea.gui.widget.qls;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

public class TextFieldWidget extends Widget {

    private StringValue widgetValue = new StringValue("");


    @Override
    public boolean updateValue(DecimalValue decimalValue) {
        //TODO: add to string
        this.widgetValue = new StringValue(decimalValue.toString());
        return true;
    }

    @Override
    public boolean updateValue(IntValue intValue) {
        this.widgetValue = new StringValue(intValue.toString());
        return true;
    }

    @Override
    public boolean updateValue(StringValue stringValue) {
        this.widgetValue = stringValue;
        return true;
    }

    public TextFieldWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public Control convertToGuiNode() {

        TextField textField = new TextField();
        this.setStyle(textField, this.questionData.getStyle());
        textField.setText(this.widgetValue.getStringValue());
        textField.setEditable(true);
        textField.setMinWidth(Widget.TEXT_WIDTH);

        if (this.questionData.isComputed()) {
            textField.setEditable(false);
        }

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), new StringValue(newValue));
        });

        textField.positionCaret(textField.getText().length());
        return textField;
    }

    //TODO: set color from styleQLS
    private void setStyle(TextField textField, Style style) {
        if (style == null)
            return;

        if (style.getWidth() != null) {
            textField.setMinWidth(style.getWidth());
        }
        if ((style.getFont() != null) && (style.getFontSize() != null)) {
            textField.setFont(new Font(style.getFont(), style.getFontSize()));
        }
    }
}
