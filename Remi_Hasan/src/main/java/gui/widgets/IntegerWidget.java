package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.variable.ExpressionVariableInteger;

public class IntegerWidget extends TextField implements WidgetInterface<ExpressionVariableInteger> {

    private final String name;

    public IntegerWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public void setExpression(ExpressionVariableInteger expression) {

    }

    @Override
    public ExpressionVariableInteger getExpression() {
        return null;
    }
}
