package gui.widgets.spinner;

import gui.widgets.WidgetUtils;
import javafx.scene.control.SpinnerValueFactory;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;

public class SpinnerDecimalWidget extends SpinnerWidget<Double> {

    public SpinnerDecimalWidget(String identifier, boolean computed) {
        // JavaFX Spinner does not work with Double.MIN_VALUE but does work with Integer.MIN_VALUE
        // Default to 0.0 and step by 0.1
        super(identifier, computed);
        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0.0, 0.1);
        this.setValueFactory(valueFactory);

        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableDecimal(null, this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.getDecimalValue());
    }
}
