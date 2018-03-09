package evaluation.value;

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
    public NumValue divide(Value right) {
        return null;
    }

    @Override
    public NumValue multiply(Value right) {
        return null;
    }

    @Override
    public NumValue subtract(Value right) {
        return null;
    }

    @Override
    public NumValue sum(Value right) {
        return null;
    }

    @Override
    public BoolValue eq(Value right) {
        return null;
    }

    @Override
    public BoolValue ge(Value right) {
        return null;
    }

    @Override
    public BoolValue gt(Value right) {
        return null;
    }

    @Override
    public BoolValue le(Value right) {
        return null;
    }

    @Override
    public BoolValue lt(Value right) {
        return null;
    }

    @Override
    public BoolValue and(Value right) {
        return null;
    }

    @Override
    public BoolValue or(Value right) {
        return null;
    }

    @Override
    public BoolValue not() {
        return null;
    }

    @Override
    public NumValue neg() {
        return null;
    }

    @Override
    public boolean equals(Value other) {
        // TODO
        return false;
    }
}
