package gui.components.widgets.spinbox;

import gui.components.widgets.WidgetUtils;
import javafx.scene.control.SpinnerValueFactory;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.DecimalConstant;

public class DecimalSpinBox extends SpinBox<Double> {

    public DecimalSpinBox() {
        // JavaFX Spinner does not work with Double.MIN_VALUE but does work with Integer.MIN_VALUE
        // Default to 0.0 and step by 0.1
        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0.0, 0.1);
        this.setValueFactory(valueFactory);

        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public Expression getExpressionValue() {
        return new DecimalConstant(this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.isUndefined() ? 0 : value.getDecimalValue());
    }
}
