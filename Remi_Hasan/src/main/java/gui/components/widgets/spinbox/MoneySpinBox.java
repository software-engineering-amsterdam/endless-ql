package gui.components.widgets.spinbox;

import gui.components.widgets.WidgetUtils;
import ql.evaluation.value.UndefinedValue;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.MoneyConstant;

public class MoneySpinBox extends DecimalSpinBox {

    public MoneySpinBox() {
        super();
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpressionValue() {
        return new MoneyConstant(this.getValue().toString());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.isUndefined() ? 0 : value.getMoneyValue().doubleValue());
    }
}
