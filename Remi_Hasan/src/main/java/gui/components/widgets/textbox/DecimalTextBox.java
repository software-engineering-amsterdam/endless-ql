package gui.components.widgets.textbox;

import gui.components.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.constant.DecimalConstant;
import ql.model.expression.constant.UndefinedConstant;

public class DecimalTextBox extends TextBox {

    public DecimalTextBox() {
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public Expression getExpressionValue() {
        if (this.getText().isEmpty()) {
            return new UndefinedConstant(ReturnType.INTEGER);
        }

        return new DecimalConstant(Double.parseDouble(this.getText()));
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getDecimalValue().toString());
    }
}
