package ql.values;

public class StringValue extends Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Value add(Value value) {
        StringValue stringValue = (StringValue) value;
        return new StringValue(this.value + stringValue.getValue());
    }

    @Override
    public Value equal(Value value) {
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(this.value.equals(getValue()));
    }

    @Override
    public Value notEqual(Value value) {
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(!this.value.equals(getValue()));
    }
}
