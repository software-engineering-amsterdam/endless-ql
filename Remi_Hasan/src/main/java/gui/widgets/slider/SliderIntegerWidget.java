package gui.widgets.slider;

import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableInteger;

public class SliderIntegerWidget extends SliderWidget {
    public SliderIntegerWidget(String identifier, boolean computed, int min, int max) {
        super(identifier, computed, min, max);

        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Only step between integers
            this.slider.setValue(newVal.intValue());

            // Integer widget, so show label next to slider as integer
            this.valueLabel.setText(String.valueOf(newVal.intValue()));
        });

        // Integer widget, so show initial value as integer as well
        this.valueLabel.setText("0");
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableInteger(null, (int) this.slider.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.slider.setValue(value.getIntValue());
    }
}
