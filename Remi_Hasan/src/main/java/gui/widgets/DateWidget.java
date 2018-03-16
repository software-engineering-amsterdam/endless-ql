package gui.widgets;

import javafx.scene.control.DatePicker;
import ql.model.expression.Expression;

public class DateWidget extends DatePicker implements WidgetInterface {

    private final String name;

    public DateWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpression() {
        return null;
    }

    @Override
    public void setExpression(String value) {

    }
}
