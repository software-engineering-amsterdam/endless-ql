package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;

public class SpinnerMoneyWidget extends SpinnerWidget<Double> {

    public SpinnerMoneyWidget() {
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableDecimal(null, this.getValue());
    }

    @Override
    public void setValue(Value value) {
        // TODO: implement
    }
}
