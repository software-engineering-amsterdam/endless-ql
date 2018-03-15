package gui.widgets;

import javafx.scene.control.DatePicker;
import ql.model.expression.variable.ExpressionVariableDate;

public class DateWidget extends DatePicker implements WidgetInterface<ExpressionVariableDate>{

    private final String name;

    public DateWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void setExpression(ExpressionVariableDate expression) {

    }

    @Override
    public ExpressionVariableDate getExpression() {
        return null;
    }
}
