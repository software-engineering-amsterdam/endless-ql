package ql.model.expression.constant;

import ql.model.expression.Expression;

public abstract class Constant<T> extends Expression {
    public final T value;

    Constant(T value) {
        this.value = value;
    }
}
