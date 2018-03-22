package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;

public class SpinnerDecimalWidget extends SpinnerWidget<Double> {

    public SpinnerDecimalWidget() {
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
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
