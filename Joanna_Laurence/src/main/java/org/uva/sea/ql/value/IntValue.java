package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;

import java.math.BigDecimal;

public class IntValue extends Value {
    private int intValue;

    public IntValue(String value) {
        this.intValue = Integer.parseInt(value);
    }

    public IntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public Value add(IntValue value) {
        return new IntValue(this.intValue + value.getIntValue());
    }

    @Override
    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.intValue)).add(value.getAmount()));
    }

    @Override
    public Value add(DecimalValue value) {
        return new DecimalValue(this.intValue + value.getDecimalValue());
    }

    @Override
    public Value divide(Value value) {
        return value.reverseDivide(this);
    }

    @Override
    public Value divide(IntValue value) {
        //TODO: Check for 0
        return new IntValue(this.intValue / value.getIntValue());
    }

    @Override
    public Value divide(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.intValue)).divide(value.getAmount()));
    }

    @Override
    public Value divide(DecimalValue value) {
        return new DecimalValue(this.intValue / value.getDecimalValue());
    }

    @Override
    public Value isEqual(Value value) {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(IntValue value) {
        return new BooleanValue(this.intValue == value.getIntValue());
    }

    @Override
    public Value isEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.intValue)).equals(value.getAmount()));
    }

    @Override
    public Value isEqual(DecimalValue value) {
        return new BooleanValue(this.intValue == value.getDecimalValue());
    }

    @Override
    public Value isGreaterOrEqual(Value value) {
        return value.isLessThan(this);
    }

    @Override
    public Value isGreaterOrEqual(IntValue value) {
        return new BooleanValue(this.intValue >= value.getIntValue());
    }

    @Override
    public Value isGreaterOrEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.intValue)).compareTo(value.getAmount()) >= 0);
    }

    @Override
    public Value isGreaterOrEqual(DecimalValue value) {
        return new BooleanValue(this.intValue >= value.getDecimalValue());
    }

    @Override
    public Value isGreaterThan(Value value) {
        return value.isLessOrEqual(this);
    }

    @Override
    public Value isGreaterThan(IntValue value) {
        return new BooleanValue(this.intValue > value.getIntValue());
    }

    @Override
    public Value isGreaterThan(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.intValue)).compareTo(value.getAmount()) > 0);
    }

    @Override
    public Value isGreaterThan(DecimalValue value) {
        return new BooleanValue(this.intValue > value.getDecimalValue());
    }

    @Override
    public Value isLessOrEqual(Value value) {
        return value.isGreaterThan(this);
    }

    @Override
    public Value isLessOrEqual(IntValue value) {
        return new BooleanValue(this.intValue <= value.getIntValue());
    }

    @Override
    public Value isLessOrEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.intValue)).compareTo(value.getAmount()) <= 0);
    }

    @Override
    public Value isLessOrEqual(DecimalValue value) {
        return new BooleanValue(this.intValue <= value.getDecimalValue());
    }

    @Override
    public Value isLessThan(Value value) {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public Value isLessThan(IntValue value) {
        return new BooleanValue(this.intValue < value.getIntValue());
    }

    @Override
    public Value isLessThan(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.intValue)).compareTo(value.getAmount()) < 0);
    }

    @Override
    public Value isLessThan(DecimalValue value) {
        return new BooleanValue(this.intValue < value.getDecimalValue());
    }

    @Override
    public Value multiply(Value value) {
        return value.multiply(this);
    }

    @Override
    public Value multiply(IntValue value) {
        return new IntValue(this.intValue * value.getIntValue());
    }

    @Override
    public Value multiply(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.intValue)).multiply(value.getAmount()));
    }

    @Override
    public Value multiply(DecimalValue value) {
        return new DecimalValue(this.intValue * value.getDecimalValue());
    }

    @Override
    public Value isNotEqual(Value value) {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(IntValue value) {
        return new BooleanValue(this.intValue != value.getIntValue());
    }

    @Override
    public Value isNotEqual(MoneyValue value) {
        return new BooleanValue(!((new BigDecimal(this.intValue)).equals(value.getAmount())));
    }

    @Override
    public Value isNotEqual(DecimalValue value) {
        return new BooleanValue(this.intValue != value.getDecimalValue());
    }

    @Override
    public Value subtract(Value value) {
        return value.reverseSubtract(this);
    }

    @Override
    public Value subtract(IntValue value) {
        return new IntValue(this.intValue - value.getIntValue());
    }

    @Override
    public Value subtract(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.intValue)).subtract(value.getAmount()));
    }

    @Override
    public Value subtract(DecimalValue value) {
        return new DecimalValue(this.intValue - value.getDecimalValue());
    }

    @Override
    public Value reverseSubtract(DecimalValue value) {
        return value.subtract(this);
    }

    @Override
    public Value reverseSubtract(IntValue value) {
        return value.subtract(this);
    }

    @Override
    public Value reverseSubtract(MoneyValue value) {
        return value.subtract(this);
    }

    @Override
    public Value reverseDivide(DecimalValue value) {
        return value.divide(this);
    }

    @Override
    public Value reverseDivide(IntValue value) {
        return value.divide(this);
    }

    @Override
    public Value reverseDivide(MoneyValue value) {
        return value.divide(this);
    }

    @Override
    public Value negate() {
        return new IntValue(this.intValue * (-1));
    }

    @Override
    public Value positive() {
        return new IntValue(this.intValue);
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
