package ql.evaluation.value;

import java.math.BigDecimal;
import java.util.Date;

public class BooleanValue extends Value<Boolean> {
    public BooleanValue(Boolean value) {
        super(value);
    }

    @Override
    public Boolean getBooleanValue() {
        return this.value;
    }

    @Override
    public Integer getIntValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to integer");
    }

    @Override
    public Double getDecimalValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to decimal");
    }

    @Override
    public BigDecimal getMoneyValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to money");
    }

    @Override
    public String getStringValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to string");
    }

    @Override
    public Date getDateValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to date");
    }

    @Override
    public NumberValue divide(Value right) {
        throw new UnsupportedOperationException("Cannot perform divide on boolean.");
    }

    @Override
    public NumberValue multiply(Value right) {
        throw new UnsupportedOperationException("Cannot perform multiply on boolean.");
    }

    @Override
    public NumberValue subtract(Value right) {
        throw new UnsupportedOperationException("Cannot perform subtract on boolean.");
    }

    @Override
    public NumberValue sum(Value right) {
        throw new UnsupportedOperationException("Cannot perform sum on boolean.");
    }

    @Override
    public BooleanValue eq(Value right) {
        return new BooleanValue(this.value.equals(right.value));
    }

    @Override
    public BooleanValue ge(Value right) {
        throw new UnsupportedOperationException("Cannot perform ge on boolean.");
    }

    @Override
    public BooleanValue gt(Value right) {
        throw new UnsupportedOperationException("Cannot perform gt on boolean.");
    }

    @Override
    public BooleanValue le(Value right) {
        throw new UnsupportedOperationException("Cannot perform le on boolean.");
    }

    @Override
    public BooleanValue lt(Value right) {
        throw new UnsupportedOperationException("Cannot perform lt on boolean.");
    }

    @Override
    public BooleanValue and(Value right) {
        return new BooleanValue(this.getBooleanValue() && right.getBooleanValue());
    }

    @Override
    public BooleanValue or(Value right) {
        return new BooleanValue(this.getBooleanValue() || right.getBooleanValue());
    }

    @Override
    public BooleanValue not() {
        return new BooleanValue(!this.getBooleanValue());
    }

    @Override
    public NumberValue neg() {
        throw new UnsupportedOperationException("Cannot perform neg on boolean.");
    }
}
