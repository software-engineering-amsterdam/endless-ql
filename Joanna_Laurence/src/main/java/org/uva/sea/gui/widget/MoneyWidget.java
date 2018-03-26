package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.MoneyValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.StringValue;

import java.math.BigDecimal;

public class MoneyWidget extends Widget {
    private StringValue widgetValue = new StringValue("");
    private String currency = "";

    public MoneyWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(MoneyValue moneyValue) {
        this.widgetValue = new StringValue(moneyValue.getAmount().toString());
        this.currency = moneyValue.getCurrency();
        return true;
    }

    @Override
    public Node convertToGuiNode() {

        TextField textField = new TextField();
        this.setStyle(textField, this.questionData.getStyle());
        textField.setText(this.widgetValue.getStringValue());
        textField.setEditable(true);
        textField.setMinWidth(BaseRenderable.TEXT_WIDTH);

        if (this.questionData.isComputed()) {
            textField.setEditable(false);
        }

        textField.textProperty().addListener((observable, oldValue, newValue) ->
                this.sendUpdateValueEvent(this.questionData.getQuestionName(), new MoneyValue(this.currency, new BigDecimal(newValue))));

        textField.positionCaret(textField.getText().length());
        return textField;
    }

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
