package ql.evaluation.value;

import java.math.BigDecimal;
import java.util.Date;

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
    public Date getDateValue() {
        throw new UnsupportedOperationException("Cannot cast string to date");
    }

    @Override
    public NumberValue divide(Value right) {
        throw new UnsupportedOperationException("Cannot perform divide on string.");
    }

    @Override
    public NumberValue multiply(Value right) {
        throw new UnsupportedOperationException("Cannot perform multiply on string.");
    }

    @Override
    public NumberValue subtract(Value right) {
        throw new UnsupportedOperationException("Cannot perform subtract on string.");
    }

    @Override
    public NumberValue sum(Value right) {
        throw new UnsupportedOperationException("Cannot perform sum on string.");
    }

    @Override
    public BooleanValue eq(Value right) {
        return new BooleanValue(this.value.equals(right.value));
    }

    @Override
    public BooleanValue ge(Value right) {
        throw new UnsupportedOperationException("Cannot perform ge on string.");
    }

    @Override
    public BooleanValue gt(Value right) {
        throw new UnsupportedOperationException("Cannot perform gt on string.");
    }

    @Override
    public BooleanValue le(Value right) {
        throw new UnsupportedOperationException("Cannot perform le on string.");
    }

    @Override
    public BooleanValue lt(Value right) {
        throw new UnsupportedOperationException("Cannot perform lt on string.");
    }

    @Override
    public BooleanValue and(Value right) {
        throw new UnsupportedOperationException("Cannot perform and on string.");
    }

    @Override
    public BooleanValue or(Value right) {
        throw new UnsupportedOperationException("Cannot perform or on string.");
    }

    @Override
    public BooleanValue not() {
        throw new UnsupportedOperationException("Cannot perform not on string.");
    }

    @Override
    public NumberValue neg() {
        throw new UnsupportedOperationException("Cannot perform neg on string.");
    }
}
