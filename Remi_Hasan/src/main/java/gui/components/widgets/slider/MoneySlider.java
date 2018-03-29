package gui.components.widgets.slider;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.MoneyConstant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneySlider extends Slider {
    public MoneySlider(double min, double max) {
        super(min, max);

        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Always set slider value to two decimal places
            BigDecimal moneyValue = new BigDecimal(newVal.toString());
            this.slider.valueProperty().setValue(moneyValue.setScale(2, RoundingMode.HALF_EVEN));
        });

        // Money widget, so show initial value as money as well
        this.valueLabel.setText("0.00");
    }

    @Override
    public Expression getExpressionValue() {
        return new MoneyConstant(String.valueOf(this.slider.getValue()));
    }

    @Override
    public void setValue(Value value) {
        this.slider.setValue(value.getMoneyValue().doubleValue());
    }
}
