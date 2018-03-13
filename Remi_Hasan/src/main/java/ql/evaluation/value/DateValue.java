package ql.evaluation.value;

import java.math.BigDecimal;

public class DateValue extends Value<String> {
    public DateValue(String value) {
        super(value);
    }

    @Override
    public Boolean getBooleanValue() {
        return null;
    }

    @Override
    public Integer getIntValue() {
        return null;
    }

    @Override
    public Double getDecimalValue() {
        return null;
    }

    @Override
    public BigDecimal getMoneyValue() {
        return null;
    }

    @Override
    public String getStringValue() {
        return null;
    }

    @Override
    public NumberValue divide(Value right) {
        return null;
    }

    @Override
    public NumberValue multiply(Value right) {
        return null;
    }

    @Override
    public NumberValue subtract(Value right) {
        return null;
    }

    @Override
    public NumberValue sum(Value right) {
        return null;
    }

    @Override
    public BooleanValue eq(Value right) {
        return null;
    }

    @Override
    public BooleanValue ge(Value right) {
        return null;
    }

    @Override
    public BooleanValue gt(Value right) {
        return null;
    }

    @Override
    public BooleanValue le(Value right) {
        return null;
    }

    @Override
    public BooleanValue lt(Value right) {
        return null;
    }

    @Override
    public BooleanValue and(Value right) {
        return null;
    }

    @Override
    public BooleanValue or(Value right) {
        return null;
    }

    @Override
    public BooleanValue not() {
        return null;
    }

    @Override
    public NumberValue neg() {
        return null;
    }

    @Override
    public boolean equals(Value other) {
        // TODO
        return false;
    }
}
