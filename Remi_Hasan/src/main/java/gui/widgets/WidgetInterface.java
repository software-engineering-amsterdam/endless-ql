package gui.widgets;

import ql.model.expression.Expression;

public interface WidgetInterface<T extends Expression> {
    public void setExpression(T expression);
    public T getExpression();
}
