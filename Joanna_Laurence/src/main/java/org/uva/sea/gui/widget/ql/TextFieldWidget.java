package org.uva.sea.gui.widget.ql;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

public class TextFieldWidget extends Widget {

    private StringValue widgetValue = new StringValue("");

    public TextFieldWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(DecimalValue decimalValue) {
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

    @Override
    public boolean updateValue(DateValue dateValue) {
        this.widgetValue = new StringValue(dateValue.getDateValue().toString());
        return true;
    }

    @Override
    public boolean updateValue(BooleanValue booleanValue) {
        this.widgetValue = new StringValue(booleanValue.toString());
        return true;
    }

    @Override
    public boolean updateValue(MoneyValue moneyValue) {
        this.widgetValue = new StringValue(moneyValue.toString());
        return true;
    }

    @Override
    public Node convertToGuiNode() {

        TextField textField = new TextField();
        this.setStyle(textField, this.questionData.getStyle());
        textField.setText(this.widgetValue.getStringValue());
        textField.setEditable(true);
        textField.setMinWidth(Widget.TEXT_WIDTH);
        textField.setFocusTraversable(false);


        if (this.questionData.isComputed()) {
            textField.setEditable(false);
        }

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.sendUpdateValueEvent(textField, this.questionData.getQuestionName(), new StringValue(newValue));
        });

        textField.positionCaret(textField.getText().length());
        return this.createRow(this.questionData.getLabel(), textField);
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
