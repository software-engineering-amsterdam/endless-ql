package nl.uva.js.qlparser.models.expressions.data;

import nl.uva.js.qlparser.models.expressions.Expression;

public interface DataExpression<T> extends Expression, Expression.Typed {
    T value();
}
