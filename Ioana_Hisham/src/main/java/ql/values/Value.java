package ql.values;

public abstract class Value {

    public abstract Object getValue();

    public Value add(Value value) {
        return new Undefined();
    }

    public Value divide(Value value) {
        return new Undefined();
    }

    public Value equal(Value value) {
        return new Undefined();
    }

    public Value greaterThan(Value value) {
        return new Undefined();
    }

    public Value greaterThanOrEqual(Value value) {
        return new Undefined();
    }

    public Value lessThan(Value value) {
        return new Undefined();
    }

    public Value lessThanOrEqual(Value value) {
        return new Undefined();
    }

    public Value and(Value value) {
        return new Undefined();
    }

    public Value or(Value value) {
        return new Undefined();
    }

    public Value multiply(Value value) {
        return new Undefined();
    }

    public Value notEqual(Value value) {
        return new Undefined();
    }

    public Value subtract(Value value) {
        return new Undefined();
    }
}
