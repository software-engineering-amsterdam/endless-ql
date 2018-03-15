package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.Expression;

public class StringWidget extends TextField implements WidgetInterface{

    private final String name;

    public StringWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void setExpression(String value) {

    }

    @Override
    public Expression getExpression() {
        return null;
    }
}
