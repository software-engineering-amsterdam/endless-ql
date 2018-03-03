package org.uva.sea.ql.value;

import java.math.BigDecimal;
import org.uva.sea.ql.visitor.BaseValueVisitor;
import org.uva.sea.ql.exceptions.EvaluationException;
import org.uva.sea.ql.parser.NodeType;


public class DecimalValue extends Value {

    private double decimalValue;

    public DecimalValue(String value) {
        this.decimalValue = Double.parseDouble(value);
    }

    public DecimalValue(double decimalValue) {

        this.decimalValue = decimalValue;
    }

    public double getDecimalValue() {
        return decimalValue;
    }

    @Override
    public Value add(Value value) throws EvaluationException {
        return value.add(this);
    }

    @Override
    public Value add(IntValue value) {
        return new DecimalValue(this.decimalValue + value.getIntValue());
    }

    @Override
    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).add(value.getAmount()));
    }

    @Override
    public Value add(DecimalValue value) {
        return new DecimalValue(this.decimalValue + value.getDecimalValue());
    }

    @Override
    public Value divide(Value value) throws EvaluationException {
        return value.reverseDivide(this);
    }

    @Override
    public Value divide(IntValue value) {
        return new DecimalValue(this.decimalValue / value.getIntValue());
    }

    @Override
    public Value divide(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).divide(value.getAmount()));
    }

    @Override
    public Value divide(DecimalValue value) {
        return new DecimalValue(this.decimalValue / value.getDecimalValue());
    }


    @Override
    public Value isEqual(Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(IntValue value) {
        return new BooleanValue(this.decimalValue == value.getIntValue());
    }

    @Override
    public Value isEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).equals(value.getAmount()));
    }

    @Override
    public Value isEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue == value.getDecimalValue());
    }

    @Override
    public Value isGreaterOrEqual(Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public Value isGreaterOrEqual(IntValue value) {
        return new BooleanValue(this.decimalValue >= value.getIntValue());
    }

    @Override
    public Value isGreaterOrEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) >= 0);
    }

    @Override
    public Value isGreaterOrEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue >= value.getDecimalValue());
    }

    @Override
    public Value isGreaterThan(Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public Value isGreaterThan(IntValue value) {
        return new BooleanValue(this.decimalValue > value.getIntValue());
    }

    @Override
    public Value isGreaterThan(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) > 0);
    }

    @Override
    public Value isGreaterThan(DecimalValue value) {
        return new BooleanValue(this.decimalValue > value.getDecimalValue());
    }

    @Override
    public Value isLessOrEqual(Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public Value isLessOrEqual(IntValue value) {
        return new BooleanValue(this.decimalValue <= value.getIntValue());
    }

    @Override
    public Value isLessOrEqual(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) <= 0);
    }

    @Override
    public Value isLessOrEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue <= value.getDecimalValue());
    }

    @Override
    public Value isLessThan(Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public Value isLessThan(IntValue value) {
        return new BooleanValue(this.decimalValue < value.getIntValue());
    }

    @Override
    public Value isLessThan(MoneyValue value) {
        return new BooleanValue((new BigDecimal(this.decimalValue)).compareTo(value.getAmount()) < 0);
    }

    @Override
    public Value isLessThan(DecimalValue value) {
        return new BooleanValue(this.decimalValue < value.getDecimalValue());
    }

    @Override
    public Value multiply(Value value) throws EvaluationException {
        return value.multiply(this);
    }

    @Override
    public Value multiply(IntValue value) {
        return new DecimalValue(this.decimalValue * value.getIntValue());
    }

    @Override
    public Value multiply(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).multiply(value.getAmount()));
    }

    @Override
    public Value multiply(DecimalValue value) {
        return new DecimalValue(this.decimalValue * value.getDecimalValue());
    }

    @Override
    public Value isNotEqual(Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(IntValue value) {
        return new BooleanValue(this.decimalValue != value.getIntValue());
    }

    @Override
    public Value isNotEqual(MoneyValue value) {
        return new BooleanValue(!((new BigDecimal(this.decimalValue)).equals(value.getAmount())));
    }

    @Override
    public Value isNotEqual(DecimalValue value) {
        return new BooleanValue(this.decimalValue != value.getDecimalValue());
    }

    @Override
    public Value subtract(Value value) throws EvaluationException {
        return value.reverseSubtract(this);
    }

    @Override
    public Value subtract(IntValue value) {
        return new DecimalValue(this.decimalValue - value.getIntValue());
    }

    @Override
    public Value subtract(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), (new BigDecimal(this.decimalValue)).subtract(value.getAmount()));
    }

    @Override
    public Value subtract(DecimalValue value) {
        return new DecimalValue(this.decimalValue - value.getDecimalValue());
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
        return new DecimalValue(this.decimalValue * (-1));
    }

    @Override
    public Value positive() {
        return new DecimalValue(this.decimalValue);
    }

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.DECIMAL;
    }
}
