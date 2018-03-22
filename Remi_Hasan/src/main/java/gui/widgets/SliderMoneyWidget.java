package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableMoney;

public class SliderMoneyWidget extends SliderWidget {
    public SliderMoneyWidget(double min, double max) {
        super(min, max);
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableMoney(null, String.valueOf(this.getValue()));
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getMoneyValue().doubleValue());
    }
}
