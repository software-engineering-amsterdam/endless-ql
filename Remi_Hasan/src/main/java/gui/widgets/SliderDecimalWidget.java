package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;
import ql.model.expression.variable.ExpressionVariableMoney;

public class SliderDecimalWidget extends SliderWidget {
    public SliderDecimalWidget(double min, double max) {
        super(min, max);
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableDecimal(null, this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getDecimalValue());
    }
}
