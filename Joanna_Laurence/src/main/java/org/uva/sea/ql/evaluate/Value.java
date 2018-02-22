package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;

public abstract class Value {

    public abstract <T> T accept(QLValueEvaluator<T> visitor);

    public Value add(Value value) {
        return value.add(this);
    }

    public Value and(Value value) {
        return value.and(this);
    }

    public Value divide(Value value) {
        return value.divide(this);
    }

    public Value isEqual(Value value) {
        return value.isEqual(this);
    }

    public Value isGreaterOrEqual(Value value) {
        return value.isGreaterOrEqual(this);
    }

    public Value isGreaterThan(Value value) {
        return value.isGreaterThan(this);
    }

    public Value isLessOrEqual(Value value) {
        return value.isLessOrEqual(this);
    }

    public Value isLessThan(Value value) {
        return value.isLessThan(this);
    }

    public Value multiply(Value value) {
        return value.multiply(this);
    }

    public abstract Value negate();

    public Value isNotEqual(Value value) {
        return value.isNotEqual(this);
    }

    public abstract Value not();

    public Value or(Value value) {
        return value.or(value);
    }

    public abstract Value positive();

    public Value subtract(Value value) {
        return value.subtract(this);
    }
}
