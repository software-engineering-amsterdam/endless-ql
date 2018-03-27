package org.uva.sea.gui.widget;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
        textField.setText(this.widgetValue.getStringValue());
        textField.setEditable(true);
        textField.setMinWidth(BaseRenderable.TEXT_WIDTH);

        if (this.questionData.isComputed()) {
            textField.setEditable(false);
        }

        textField.textProperty().addListener((observable, oldValue, newValue) ->
                this.sendUpdateValueEvent(this.questionData.getQuestionName(), new MoneyValue(this.currency, new BigDecimal(newValue))));

        textField.positionCaret(textField.getText().length());

        return wrapTextFieldWithCurrency(textField, this.currency);
    }

    private Node wrapTextFieldWithCurrency(TextField textField, String currency) {
        HBox wrapper = new HBox();
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().add(new Label(currency));
        wrapper.getChildren().add(textField);
        return wrapper;
    }
}
