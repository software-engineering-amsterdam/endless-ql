package ql.model.expression.constant;

import ql.model.expression.Expression;

public abstract class Constant<T> extends Expression {
    private final T value;

    Constant(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
