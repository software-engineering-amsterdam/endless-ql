package gui.elements.widgets.spinner;

import gui.elements.widgets.WidgetUtils;
import javafx.scene.control.SpinnerValueFactory;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableInteger;

public class SpinnerIntegerWidget extends SpinnerWidget<Integer> {

    public SpinnerIntegerWidget() {
        // Default to 0 and step by 1
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
        this.setValueFactory(valueFactory);

        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableInteger(this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.getIntValue());
    }
}
