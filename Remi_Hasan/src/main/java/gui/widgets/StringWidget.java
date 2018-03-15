package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableString;

public class StringWidget extends TextField implements WidgetInterface {

    private final String name;

    public StringWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpression() {
        return new ExpressionVariableString(null, getText());
    }

    @Override
    public void setExpression(String value) {
        this.setText(value);
    }
}
