package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.variable.ExpressionVariableDecimal;

public class DecimalWidget extends TextField implements WidgetInterface<ExpressionVariableDecimal>{

    private final String name;

    public DecimalWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public void setExpression(ExpressionVariableDecimal expression) {

    }

    @Override
    public ExpressionVariableDecimal getExpression() {
        return null;
    }
}
