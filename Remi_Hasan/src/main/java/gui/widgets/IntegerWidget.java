package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.Expression;

public class IntegerWidget extends TextField implements WidgetInterface{

    private final String name;

    public IntegerWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public void setExpression(String value) {

    }

    @Override
    public Expression getExpression() {
        return null;
    }
}
