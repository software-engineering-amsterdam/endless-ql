package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class IntValue extends Value {
    private final int intValue;

    public IntValue(String value) {
        this.intValue = Integer.parseInt(value);
    }

    public IntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    @Override
    public Value add(Value value) throws EvaluationException {
        return value.add(this);
    }

    @Override
    public Value add(IntValue value) {
        return new IntValue(this.intValue + value.intValue);
    }

    @Override
    public Value add(DecimalValue value) {
        return new DecimalValue(this.intValue + value.getDecimalValue());
    }

    @Override
    public Value divide(Value value) throws EvaluationException {
        return value.divide(this);
    }

    @Override
    public Value divide(IntValue value) throws EvaluationException {
        if (value.intValue == 0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue((double) this.intValue / value.intValue);
    }

    @Override
    public Value divide(DecimalValue value) throws EvaluationException {
        if (value.getDecimalValue() == 0.0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue(this.intValue / value.getDecimalValue());
    }

    @Override
    public Value isEqual(Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(IntValue value) {
        return new BooleanValue(this.intValue == value.intValue);
    }

    @Override
    public Value isEqual(DecimalValue value) {
        return new BooleanValue(this.intValue == value.getDecimalValue());
    }

    @Override
    public Value isGreaterOrEqual(Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public Value isGreaterOrEqual(IntValue value) {
        return new BooleanValue(this.intValue >= value.intValue);
    }

    @Override
    public Value isGreaterOrEqual(DecimalValue value) {
        return new BooleanValue(this.intValue >= value.getDecimalValue());
    }

    @Override
    public Value isGreaterThan(Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public Value isGreaterThan(IntValue value) {
        return new BooleanValue(this.intValue > value.intValue);
    }

    @Override
    public Value isGreaterThan(DecimalValue value) {
        return new BooleanValue(this.intValue > value.getDecimalValue());
    }

    @Override
    public Value isLessOrEqual(Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public Value isLessOrEqual(IntValue value) {
        return new BooleanValue(this.intValue <= value.intValue);
    }

    @Override
    public Value isLessOrEqual(DecimalValue value) {
        return new BooleanValue(this.intValue <= value.getDecimalValue());
    }

    @Override
    public Value isLessThan(Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public Value isLessThan(IntValue value) {
        return new BooleanValue(this.intValue < value.intValue);
    }

    @Override
    public Value isLessThan(DecimalValue value) {
        return new BooleanValue(this.intValue < value.getDecimalValue());
    }

    @Override
    public Value multiply(Value value) throws EvaluationException {
        return value.multiply(this);
    }

    @Override
    public Value multiply(IntValue value) {
        return new IntValue(this.intValue * value.intValue);
    }

    @Override
    public Value multiply(DecimalValue value) {
        return new DecimalValue(this.intValue * value.getDecimalValue());
    }

    @Override
    public Value isNotEqual(Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(IntValue value) {
        return new BooleanValue(this.intValue != value.intValue);
    }

    @Override
    public Value isNotEqual(DecimalValue value) {
        return new BooleanValue(this.intValue != value.getDecimalValue());
    }

    @Override
    public Value subtract(IntValue value) {
        return new IntValue(this.intValue - value.intValue);
    }

    @Override
    public Value subtract(DecimalValue value) {
        return new DecimalValue(this.intValue - value.getDecimalValue());
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
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.INTEGER;
    }

    @Override
    public String toString() {
        return String.valueOf(this.intValue);
    }

    public IntValue clone() throws CloneNotSupportedException {
        return (IntValue) super.clone();
    }
}
