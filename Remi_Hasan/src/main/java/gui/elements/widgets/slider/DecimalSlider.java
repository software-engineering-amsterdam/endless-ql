package gui.elements.widgets.slider;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.DecimalConstant;

public class DecimalSlider extends Slider {
    public DecimalSlider(double min, double max) {
        super(min, max);

        // Display value in label next to slider
        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> valueLabel.setText(newVal.toString()));
    }

    @Override
    public Expression getExpressionValue() {
        return new DecimalConstant(this.slider.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.slider.setValue(value.getDecimalValue());
    }
}
