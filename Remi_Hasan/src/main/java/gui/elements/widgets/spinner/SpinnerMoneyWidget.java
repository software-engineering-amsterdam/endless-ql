package gui.elements.widgets.spinner;

import gui.elements.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableMoney;

public class SpinnerMoneyWidget extends SpinnerDecimalWidget {

    public SpinnerMoneyWidget() {
        super();
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableMoney(this.getValue().toString());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.getMoneyValue().doubleValue());
    }
}
