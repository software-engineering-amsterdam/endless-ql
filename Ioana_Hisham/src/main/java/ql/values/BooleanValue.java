package ql.values;

public class BooleanValue extends Value {
    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value value) {
        BooleanValue booleanValue = ((BooleanValue) value);
        return new BooleanValue(this.value == booleanValue.getValue());
    }

    @Override
    public BooleanValue and(Value value) {
        BooleanValue booleanValue = ((BooleanValue) value);
        return new BooleanValue(this.value && booleanValue.getValue());
    }

    @Override
    public BooleanValue or(Value value) {
        BooleanValue booleanValue = ((BooleanValue) value);
        return new BooleanValue(this.value || booleanValue.getValue());
    }

    @Override
    public Value notEqual(Value value) {
        BooleanValue booleanValue = (BooleanValue) value;
        return new BooleanValue(this.value != booleanValue.getValue());
    }
}
