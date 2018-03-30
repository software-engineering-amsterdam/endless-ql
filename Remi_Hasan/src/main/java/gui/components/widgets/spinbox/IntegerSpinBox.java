package gui.components.widgets.spinbox;

import gui.components.widgets.WidgetUtils;
import javafx.scene.control.SpinnerValueFactory;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.IntegerConstant;

public class IntegerSpinBox extends SpinBox<Integer> {

    public IntegerSpinBox() {
        // Default to 0 and step by 1
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
        this.setValueFactory(valueFactory);

        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpressionValue() {
        return new IntegerConstant(this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.getValueFactory().setValue(value.getIntegerValue());
    }
}
