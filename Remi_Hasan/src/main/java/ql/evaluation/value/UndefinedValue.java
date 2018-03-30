package ql.evaluation.value;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UndefinedValue extends Value<Boolean> {

    public UndefinedValue() {
        super(null);
    }

    @Override
    public Boolean getBooleanValue() {
        return false;
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
    public LocalDate getDateValue() {
        return null;
    }

    @Override
    public Value divide(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value multiply(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value subtract(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value sum(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value eq(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value ge(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value gt(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value le(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value lt(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value and(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value or(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value not() {
        return new UndefinedValue();
    }

    @Override
    public Value neg() {
        return new UndefinedValue();
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
}
