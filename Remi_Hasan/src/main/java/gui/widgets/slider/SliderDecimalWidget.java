package gui.widgets.slider;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;

public class SliderDecimalWidget extends SliderWidget {
    public SliderDecimalWidget(double min, double max) {
        super(min, max);

        // Display value in label next to slider
        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> valueLabel.setText(newVal.toString()));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableDecimal(null, this.slider.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.slider.setValue(value.getDecimalValue());
    }
}
