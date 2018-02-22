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

    public Value add(IntValue value) {
        return new ErrorValue("Cannot add integer to boolean");
    }

    public Value add(DecimalValue value) {
        return new ErrorValue("Cannot add decimal to boolean");
    }

    public Value add(MoneyValue value) {
        return new ErrorValue("Cannot add money to boolean");
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Cannot add boolean to boolean");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Cannot add date to boolean");
    }

    public Value add(StringValue value) {
        return new ErrorValue("Cannot add string to boolean");
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value negate() {
        return new ErrorValue("Negate operator cannot be applied on a boolean value");
    }

    @Override
    public Value not() {
        return new BooleanValue(!this.getBooleanValue());
    }

    @Override
    public Value positive() {
        return new ErrorValue("Positive operator cannot be applied on a boolean value");
    }
}
