package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;
import ql.model.expression.variable.ExpressionVariableInteger;

public class SliderIntegerWidget extends SliderWidget {
    public SliderIntegerWidget(int min, int max) {
        super(min, max);

        // Only step between integers
        this.valueProperty().addListener((obs, oldVal, newVal) -> this.setValue(newVal.intValue()));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableInteger(null, (int) this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getIntValue());
    }
}
