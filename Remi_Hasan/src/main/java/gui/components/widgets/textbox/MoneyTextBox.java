package gui.components.widgets.textbox;

import gui.components.widgets.GUIWidget;
import gui.components.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.constant.MoneyConstant;
import ql.model.expression.constant.UndefinedConstant;

public class MoneyTextBox extends TextBox implements GUIWidget {

    public MoneyTextBox() {
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpressionValue() {
        if (this.getText().isEmpty()) {
            return new UndefinedConstant(ReturnType.MONEY);
        }

        return new MoneyConstant(this.getText());
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getMoneyValue().toString());
    }
}
