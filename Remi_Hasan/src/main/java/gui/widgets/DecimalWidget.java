package gui.widgets;

import javafx.scene.control.TextField;
import ql.model.expression.Expression;

public class DecimalWidget extends TextField implements WidgetInterface{

    private final String name;

    public DecimalWidget(String name) {
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public void setExpression(String value) {

    }

    @Override
    public Expression getExpression() {
        return null;
    }
}
