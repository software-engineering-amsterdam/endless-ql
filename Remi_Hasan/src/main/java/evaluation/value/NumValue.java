package evaluation.value;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumValue extends Value<BigDecimal> {

    public NumValue(BigDecimal value) {
        super(value);
    }

    public NumValue(Integer value) {
        super(new BigDecimal(value.toString()));
    }

    public NumValue(Double value) {
        super(new BigDecimal(value.toString()));
    }

    @Override
    public Boolean getBooleanValue() {
        throw new UnsupportedOperationException("Cannot cast number to boolean");
    }

    @Override
    public Integer getIntValue() {
        return this.value.intValue();
    }

    @Override
    public Double getDecimalValue() {
        return this.value.doubleValue();
    }

    @Override
    public BigDecimal getMoneyValue() {
        return this.value.setScale(2, RoundingMode.CEILING);
    }

    @Override
    public String getStringValue() {
        throw new UnsupportedOperationException("Cannot cast number to string");
    }

    @Override
    public Value divide(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.divide(rightValue.value, MathContext.DECIMAL128));
    }

    @Override
    public Value multiply(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.multiply(rightValue.value));
    }

    @Override
    public Value subtract(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.subtract(rightValue.value));
    }

    @Override
    public Value sum(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.add(rightValue.value));
    }

    @Override
    public Value eq(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.equals(rightValue.value));
    }

    @Override
    public Value ge(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) >= 0);
    }

    @Override
    public Value gt(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) > 0);
    }

    @Override
    public Value le(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) <= 0);
    }

    @Override
    public Value lt(Value right) {
        if (right.isUndefined())
            return new UndefinedValue();

        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) < 0);
    }

    @Override
    public Value and(Value right) {
        throw new UnsupportedOperationException("Cannot perform and on integer.");
    }

    @Override
    public Value or(Value right) {
        throw new UnsupportedOperationException("Cannot perform or on integer.");
    }

    @Override
    public Value not() {
        throw new UnsupportedOperationException("Cannot perform not on integer.");
    }

    @Override
    public Value neg() {
        return new NumValue(this.value.multiply(new BigDecimal(-1.0)));
    }

    @Override
    public boolean equals(Value other) {
        return this.value.equals(other.value);
    }
}
