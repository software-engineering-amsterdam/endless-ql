package gui.widgets.textbox;

import gui.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableDecimal;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class TextboxDecimalWidget extends TextboxWidget {

    public TextboxDecimalWidget() {
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public Expression getExpressionValue() {
        if (this.getText().isEmpty()) {
            return new ExpressionVariableUndefined(ReturnType.INTEGER);
        }

        return new ExpressionVariableDecimal(Double.parseDouble(this.getText()));
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getDecimalValue().toString());
    }
}
