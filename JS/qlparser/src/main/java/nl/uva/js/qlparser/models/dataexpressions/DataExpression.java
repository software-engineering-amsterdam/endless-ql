package nl.uva.js.qlparser.models.dataexpressions;

import nl.uva.js.qlparser.models.Expression;

public interface DataExpression<T> extends Expression, Expression.Typed {
    T value();
}
