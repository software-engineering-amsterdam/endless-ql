package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.variable.ExpressionVariableString;

public class StringWidget extends TextField implements WidgetInterface<ExpressionVariableString> {

    private final String name;

    public StringWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void setExpression(ExpressionVariableString expression) {

    }

    @Override
    public ExpressionVariableString getExpression() {
        return null;
    }
}
