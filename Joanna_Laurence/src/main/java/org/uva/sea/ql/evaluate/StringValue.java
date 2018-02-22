package org.uva.sea.ql.evaluate;

public class StringValue extends Value {

    private String stringValue;

    public StringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Value add(IntValue value) {
        return new ErrorValue("Cannot add integer to string");
    }

    public Value add(DecimalValue value) {
        return new ErrorValue("Cannot add decimal to string");
    }

    public Value add(MoneyValue value) {
        return new ErrorValue("Cannot add money to string");
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Cannot add boolean to string");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Cannot add date to string");
    }

    public Value add(StringValue value) {
        return new StringValue(this.getStringValue().concat(value.getStringValue()));
    }
}
