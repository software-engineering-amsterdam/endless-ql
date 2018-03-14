package ql.evaluation.value;

import java.math.BigDecimal;
import java.util.Date;

public class DateValue extends Value<Date> {
    public DateValue(Date value) {
        super(value);
    }

    @Override
    public Boolean getBooleanValue() {
        throw new UnsupportedOperationException("Cannot cast date to boolean.");
    }

    @Override
    public Integer getIntValue() {
        throw new UnsupportedOperationException("Cannot cast date to integer.");
    }

    @Override
    public Double getDecimalValue() {
        throw new UnsupportedOperationException("Cannot cast date to decimal.");
    }

    @Override
    public BigDecimal getMoneyValue() {
        throw new UnsupportedOperationException("Cannot cast date to money.");
    }

    @Override
    public String getStringValue() {
        throw new UnsupportedOperationException("Cannot cast date to string.");
    }

    @Override
    public Date getDateValue() {
        return this.value;
    }

    @Override
    public NumberValue divide(Value right) {
        throw new UnsupportedOperationException("Cannot perform divide on date.");
    }

    @Override
    public NumberValue multiply(Value right) {
        throw new UnsupportedOperationException("Cannot perform multiply on date.");
    }

    @Override
    public NumberValue subtract(Value right) {
        throw new UnsupportedOperationException("Cannot perform subtract on date.");
    }

    @Override
    public NumberValue sum(Value right) {
        throw new UnsupportedOperationException("Cannot perform add on date.");
    }

    @Override
    public BooleanValue eq(Value right) {
        return new BooleanValue(this.value.equals(right.value));
    }

    @Override
    public BooleanValue ge(Value right) {
        throw new UnsupportedOperationException("Cannot perform ge on date.");
    }

    @Override
    public BooleanValue gt(Value right) {
        throw new UnsupportedOperationException("Cannot perform gt on date.");
    }

    @Override
    public BooleanValue le(Value right) {
        throw new UnsupportedOperationException("Cannot perform le on date.");
    }

    @Override
    public BooleanValue lt(Value right) {
        throw new UnsupportedOperationException("Cannot perform lt on date.");
    }

    @Override
    public BooleanValue and(Value right) {
        throw new UnsupportedOperationException("Cannot perform and on date.");
    }

    @Override
    public BooleanValue or(Value right) {
        throw new UnsupportedOperationException("Cannot perform or on date.");
    }

    @Override
    public BooleanValue not() {
        throw new UnsupportedOperationException("Cannot perform not on date.");
    }

    @Override
    public NumberValue neg() {
        throw new UnsupportedOperationException("Cannot perform neg on date.");
    }
}
