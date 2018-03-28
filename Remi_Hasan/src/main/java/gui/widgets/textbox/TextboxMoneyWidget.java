package gui.widgets.textbox;

import gui.widgets.GUIWidget;
import gui.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableMoney;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class TextboxMoneyWidget extends TextboxWidget implements GUIWidget {

    public TextboxMoneyWidget() {
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpressionValue() {
        if (this.getText().isEmpty()) {
            return new ExpressionVariableUndefined(ReturnType.MONEY);
        }

        return new ExpressionVariableMoney(this.getText());
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getMoneyValue().toString());
    }
}
