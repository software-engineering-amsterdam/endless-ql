package gui.widgets;

import javafx.scene.control.CheckBox;
import ql.model.expression.variable.ExpressionVariableBoolean;

public class CheckboxWidget extends CheckBox implements WidgetInterface<ExpressionVariableBoolean>{

    private final String name;

    public CheckboxWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void setExpression(ExpressionVariableBoolean expression) {
        this.setSelected(expression.value);
    }

    @Override
    public ExpressionVariableBoolean getExpression() {
        return new ExpressionVariableBoolean(null, this.isSelected());
    }
}
