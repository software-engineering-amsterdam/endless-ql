package gui.widgets;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableMoney;

public class SpinnerMoneyWidget extends SpinnerDecimalWidget {

    public SpinnerMoneyWidget(double step) {
        super(step);
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableMoney(null, this.getValue().toString());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.getMoneyValue().doubleValue());
    }
}
