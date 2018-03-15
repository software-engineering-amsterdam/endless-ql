package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableInteger;

public class IntegerWidget extends TextField implements WidgetInterface {

    private final String name;

    public IntegerWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpression() {
        return new ExpressionVariableInteger(null, Integer.parseInt(getText()));
    }

    @Override
    public void setExpression(String value) {
        this.setText(value);
    }
}
