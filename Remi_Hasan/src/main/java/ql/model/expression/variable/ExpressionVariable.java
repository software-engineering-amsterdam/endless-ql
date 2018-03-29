package ql.model.expression.variable;

import ql.model.expression.Expression;

public abstract class ExpressionVariable<T> extends Expression {
    public final T value;

    ExpressionVariable(T value) {
        this.value = value;
    }
}
