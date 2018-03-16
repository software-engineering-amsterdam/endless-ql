package gui.widgets;

import ql.model.expression.Expression;

public interface WidgetInterface {
    public Expression getExpression();

    public void setExpression(String value);
}
