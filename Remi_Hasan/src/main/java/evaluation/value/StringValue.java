package evaluation.value;

import java.math.BigDecimal;

public class StringValue extends Value<String> {

    public StringValue(String value) {
        super(value);
    }

    @Override
    public Boolean getBooleanValue() {
        throw new UnsupportedOperationException("Cannot cast string to boolean");
    }

    @Override
    public Integer getIntValue() {
        throw new UnsupportedOperationException("Cannot cast string to integer");
    }

    @Override
    public Double getDecimalValue() {
        throw new UnsupportedOperationException("Cannot cast string to decimal");
    }

    @Override
    public BigDecimal getMoneyValue() {
        throw new UnsupportedOperationException("Cannot cast string to money");
    }

    @Override
    public String getStringValue() {
        return this.value;
    }

    @Override
    public NumValue divide(Value right) {
        throw new UnsupportedOperationException("Cannot perform divide on string.");
    }

    @Override
    public NumValue multiply(Value right) {
        throw new UnsupportedOperationException("Cannot perform multiply on string.");
    }

    @Override
    public NumValue subtract(Value right) {
        throw new UnsupportedOperationException("Cannot perform subtract on string.");
    }

    @Override
    public NumValue sum(Value right) {
        throw new UnsupportedOperationException("Cannot perform sum on string.");
    }

    @Override
    public BoolValue eq(Value right) {
        return new BoolValue(this.value.equals(right.value));
    }

    @Override
    public BoolValue ge(Value right) {
        throw new UnsupportedOperationException("Cannot perform ge on string.");
    }

    @Override
    public BoolValue gt(Value right) {
        throw new UnsupportedOperationException("Cannot perform gt on string.");
    }

    @Override
    public BoolValue le(Value right) {
        throw new UnsupportedOperationException("Cannot perform le on string.");
    }

    @Override
    public BoolValue lt(Value right) {
        throw new UnsupportedOperationException("Cannot perform lt on string.");
    }

    @Override
    public BoolValue and(Value right) {
        throw new UnsupportedOperationException("Cannot perform and on string.");
    }

    @Override
    public BoolValue or(Value right) {
        throw new UnsupportedOperationException("Cannot perform or on string.");
    }

    @Override
    public BoolValue not() {
        throw new UnsupportedOperationException("Cannot perform not on string.");
    }

    @Override
    public NumValue neg() {
        throw new UnsupportedOperationException("Cannot perform neg on string.");
    }

    @Override
    public boolean equals(Value other) {
        return this.value.equals(other.value);
    }
}
