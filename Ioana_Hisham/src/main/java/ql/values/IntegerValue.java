package ql.values;

public class IntegerValue extends Value {
    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Value add(Value value) {
        if (value.getValue() == null) {
            return new Undefined();
        }
        IntegerValue integerValue = (IntegerValue) value;
        return new IntegerValue(this.value + integerValue.getValue());
    }

    @Override
    public Value divide(Value value) {
        if (value.getValue() == null) {
            return new Undefined();
        }
        IntegerValue integerValue = (IntegerValue) value;
        return new IntegerValue(this.value / integerValue.getValue());
    }

    @Override
    public Value equal(Value value) {
        IntegerValue integerValue = (IntegerValue) value;
        return new BooleanValue(this.value == integerValue.getValue());
    }

    @Override
    public Value greaterThan(Value value) {
        IntegerValue integerValue = (IntegerValue) value;
        return new BooleanValue(this.value > integerValue.getValue());
    }

    @Override
    public Value greaterThanOrEqual(Value value) {
        IntegerValue integerValue = (IntegerValue) value;
        return new BooleanValue(this.value >= integerValue.getValue());
    }

    @Override
    public Value lessThan(Value value) {
        IntegerValue integerValue = (IntegerValue) value;
        return new BooleanValue(this.value < integerValue.getValue());
    }

    @Override
    public Value lessThanOrEqual(Value value) {
        IntegerValue integerValue = (IntegerValue) value;
        return new BooleanValue(this.value <= integerValue.getValue());
    }

    @Override
    public Value multiply(Value value) {
        if (value.getValue() == null) {
            return new Undefined();
        }
        IntegerValue integerValue = (IntegerValue) value;
        return new IntegerValue(this.value * integerValue.getValue());
    }

    @Override
    public Value notEqual(Value value) {
        IntegerValue integerValue = (IntegerValue) value;
        return new BooleanValue(this.value != integerValue.getValue());
    }

    @Override
    public Value subtract(Value value) {
        if (value.getValue() == null) {
            return new Undefined();
        }
        IntegerValue integerValue = (IntegerValue) value;
        return new IntegerValue(this.value - integerValue.getValue());
    }
}
