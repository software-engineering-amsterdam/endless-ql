package org.uva.sea.ql.value;

import java.math.BigDecimal;
import org.uva.sea.ql.QLValueEvaluator;


public class DecimalValue extends Value {

    private double decimalValue;

    public DecimalValue(double decimalValue) {

        this.decimalValue = decimalValue;
    }

    public double getDecimalValue() {
        return decimalValue;
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    public Value add(IntValue value) {
        return new DecimalValue(this.decimalValue + value.getIntValue());
    }

    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).add(value.getAmount()));
    }

    public Value add(DecimalValue value) {
        return new DecimalValue(this.decimalValue + value.getDecimalValue());
    }

    @Override
    public Value divide(Value value) {
        return value.divide(this);
    }

    public Value divide(IntValue value) {
        return new DecimalValue(this.decimalValue / value.getIntValue());
    }

    public Value divide(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).divide(value.getAmount()));
    }

    public Value divide(DecimalValue value) {
        return new DecimalValue(this.decimalValue / value.getDecimalValue());
    }

    @Override
    public Value isEqual(Value value) {
        return value.isEqual(this);
    }

    public Value isEqual(IntValue value) {
        return new BooleanValue(this.decimalValue == value.getIntValue());
    }

    public Value isEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).equals(value.getAmount()));
    }

    public Value isEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue == value.getDecimalValue());
    }

    @Override
    public Value isGreaterOrEqual(Value value) {
        return value.isGreaterOrEqual(this);
    }

    public Value isGreaterOrEqual(IntValue value) {
        return new BooleanValue(this.decimalValue >= value.getIntValue());
    }

    public Value isGreaterOrEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) >= 0);
    }

    public Value isGreaterOrEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue >= value.getDecimalValue());
    }

    @Override
    public Value isGreaterThan(Value value) {
        return value.isGreaterThan(this);
    }

    public Value isGreaterThan(IntValue value) {
        return new BooleanValue(this.decimalValue > value.getIntValue());
    }

    public Value isGreaterThan(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) > 0);
    }

    public Value isGreaterThan(DecimalValue value) {
        return new BooleanValue(this.decimalValue > value.getDecimalValue());
    }

    @Override
    public Value isLessOrEqual(Value value) {
        return value.isLessOrEqual(this);
    }

    public Value isLessOrEqual(IntValue value) {
        return new BooleanValue(this.decimalValue <= value.getIntValue());
    }

    public Value isLessOrEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) <= 0);
    }

    public Value isLessOrEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue <= value.getDecimalValue());
    }

    @Override
    public Value isLessThan(Value value) {
        return value.isLessThan(this);
    }

    public Value isLessThan(IntValue value) {
        return new BooleanValue(this.decimalValue < value.getIntValue());
    }

    public Value isLessThan(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) < 0);
    }

    public Value isLessThan(DecimalValue value) {
        return new BooleanValue(this.decimalValue < value.getDecimalValue());
    }

    @Override
    public Value multiply(Value value) {
        return value.multiply(this);
    }

    public Value multiply(IntValue value) {
        return new DecimalValue(this.decimalValue * value.getIntValue());
    }

    public Value multiply(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).multiply(value.getAmount()));
    }

    public Value multiply(DecimalValue value) {
        return new DecimalValue(this.decimalValue * value.getDecimalValue());
    }

    @Override
    public Value isNotEqual(Value value) {
        return value.isNotEqual(this);
    }

    public Value isNotEqual(IntValue value) {
        return new BooleanValue(this.decimalValue != value.getIntValue());
    }

    public Value isNotEqual(MoneyValue value) {
        return new BooleanValue(!((new BigDecimal(this.decimalValue)).equals(value.getAmount())));
    }

    public Value isNotEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue != value.getDecimalValue());
    }

    @Override
    public Value subtract(Value value) {
        return value.subtract(this);
    }

    public Value subtract(IntValue value) {
        return new DecimalValue(this.decimalValue - value.getIntValue());
    }

    public Value subtract(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).subtract(value.getAmount()));
    }

    public Value subtract(DecimalValue value) {
        return new DecimalValue(this.decimalValue - value.getDecimalValue());
    }

    @Override
    public Value negate() {
        return new DecimalValue(this.decimalValue * (-1));
    }

    @Override
    public Value positive() {
        return new DecimalValue(this.decimalValue);
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
