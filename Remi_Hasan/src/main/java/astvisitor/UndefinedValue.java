package astvisitor;

import java.math.BigDecimal;

public class UndefinedValue extends Value<Boolean> {

    UndefinedValue(Boolean value) {
        super(value);
    }

    @Override
    public boolean isUndefined() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public Boolean getBooleanValue() {
        return false;
    }

    @Override
    public Integer getIntValue() {
        return 0;
    }

    @Override
    public Double getDecimalValue() {
        return 0.0;
    }

    @Override
    public BigDecimal getMoneyValue() {
        return new BigDecimal(0);
    }

    @Override
    public String getStringValue() {
        return "";
    }

    @Override
    public Value divide(Value right) {
        return new UndefinedValue(false);
    }

    @Override
    public Value multiply(Value right) {
        return new UndefinedValue(false);
    }

    @Override
    public Value subtract(Value right) {
        return new UndefinedValue(false);
    }

    @Override
    public Value sum(Value right) {
        return new UndefinedValue(false);
    }

    @Override
    public Value eq(Value right) {
        return new UndefinedValue(false);
    }

    @Override
    public Value ge(Value right) {
        return new BoolValue(false);
    }

    @Override
    public Value gt(Value right) {
        return new BoolValue(false);
    }

    @Override
    public Value le(Value right) {
        return new BoolValue(false);
    }

    @Override
    public Value lt(Value right) {
        return new BoolValue(false);
    }

    @Override
    public Value and(Value right) {
        return new BoolValue(false);
    }

    @Override
    public Value or(Value right) {
        return new BoolValue(right.getBooleanValue());
    }

    @Override
    public Value not() {
        return new BoolValue(true);
    }

    @Override
    public Value neg() {
        return new UndefinedValue(false);
    }
}
