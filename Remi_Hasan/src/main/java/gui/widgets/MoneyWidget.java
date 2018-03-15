package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.variable.ExpressionVariableMoney;

public class MoneyWidget extends TextField implements WidgetInterface<ExpressionVariableMoney> {

    private final String name;

    public MoneyWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public void setExpression(ExpressionVariableMoney expression) {

    }

    @Override
    public ExpressionVariableMoney getExpression() {
        return null;
    }
}
