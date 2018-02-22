package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;

public class ErrorValue extends Value {
    private String error;

    public ErrorValue(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value negate() {
        return this;
    }

    @Override
    public Value not() {
        return this;
    }

    @Override
    public Value positive() {
        return this;
    }
}
