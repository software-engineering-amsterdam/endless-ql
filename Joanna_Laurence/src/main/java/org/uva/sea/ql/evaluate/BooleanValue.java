package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;

public class BooleanValue extends Value {
    boolean booleanValue;

    public BooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    @Override
    public Value and(Value value) {
        return value.and(this);
    }

    public Value and(BooleanValue value) {
        return new BooleanValue(this.booleanValue && value.getBooleanValue());
    }

    @Override
    public Value isEqual(Value value) {
        return value.isEqual(this);
    }

    public Value isEqual(BooleanValue value) {
        return new BooleanValue(this.booleanValue == value.getBooleanValue());
    }

    @Override
    public Value isNotEqual(Value value) {
        return value.isNotEqual(this);
    }

    public Value isNotEqual(BooleanValue value) {
        return new BooleanValue(this.booleanValue != value.getBooleanValue());
    }

    @Override
    public Value or(Value value) {
        return value.or(value);
    }

    public Value or(BooleanValue value) {
        return new BooleanValue(this.booleanValue || value.getBooleanValue());
    }

    @Override
    public Value not() {
        return new BooleanValue(!this.getBooleanValue());
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
