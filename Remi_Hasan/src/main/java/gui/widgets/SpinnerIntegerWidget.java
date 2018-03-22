package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableInteger;

public class SpinnerIntegerWidget extends SpinnerWidget<Integer> {

    public SpinnerIntegerWidget() {
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableInteger(null, this.getValue());
    }

    @Override
    public void setValue(Value value) {
        // TODO: implement
    }
}
