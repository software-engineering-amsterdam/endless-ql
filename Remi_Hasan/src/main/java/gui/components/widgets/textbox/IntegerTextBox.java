package gui.components.widgets.textbox;

import gui.components.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.constant.IntegerConstant;
import ql.model.expression.constant.UndefinedConstant;

public class IntegerTextBox extends TextBox {

    public IntegerTextBox() {
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpressionValue() {
        if (this.getText().isEmpty()) {
            return new UndefinedConstant(ReturnType.INTEGER);
        }

        return new IntegerConstant(Integer.parseInt(this.getText()));
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getIntValue().toString());
    }
}
