package nl.uva.js.qlparser.models.expressions.data;

import nl.uva.js.qlparser.models.expressions.Expression;
import nl.uva.js.qlparser.wrappers.logic.ValueChangeListener;

public interface DataExpression<T> extends Expression, Expression.Typed {
    T value();
    void addChangeListener(ValueChangeListener listener);
}
