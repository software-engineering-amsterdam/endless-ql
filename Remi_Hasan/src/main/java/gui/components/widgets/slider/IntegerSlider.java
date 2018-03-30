package gui.components.widgets.slider;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.IntegerConstant;

public class IntegerSlider extends Slider {
    public IntegerSlider(int min, int max) {
        super(min, max);

        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Only step between integers
            this.slider.setValue(newVal.intValue());

            // Integer widget, so show label next to slider as integer
            this.valueLabel.setText(String.valueOf(newVal.intValue()));
        });

        // Integer widget, so show initial value as integer as well
        this.valueLabel.setText(String.valueOf(min));
    }

    @Override
    public Expression getExpressionValue() {
        return new IntegerConstant((int) this.slider.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.slider.setValue(value.isUndefined() ? this.slider.getMin() : value.getIntegerValue());
    }
}
