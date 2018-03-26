package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class IntValue extends Value {
    private final int intValue;

    public IntValue(final String value) {
        this.intValue = Integer.parseInt(value);
    }

    public IntValue(final int intValue) {
        this.intValue = intValue;
    }

    public final int getIntValue() {
        return this.intValue;
    }

    @Override
    public final Value add(final Value value) throws EvaluationException {
        return value.add(this);
    }

    @Override
    public final Value add(final IntValue value) {
        return new IntValue(this.intValue + value.intValue);
    }

    @Override
    public final Value add(final DecimalValue value) {
        return new DecimalValue(this.intValue + value.getDecimalValue());
    }

    @Override
    public final Value divide(final Value value) throws EvaluationException {
        return value.divide(this);
    }

    @Override
    public final Value divide(final IntValue value) throws EvaluationException {
        if (value.intValue == 0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue((double) this.intValue / value.intValue);
    }

    @Override
    public final Value divide(final DecimalValue value) throws EvaluationException {
        if (value.getDecimalValue() == 0.0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue(this.intValue / value.getDecimalValue());
    }

    @Override
    public final Value isEqual(final Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public final Value isEqual(final IntValue value) {
        return new BooleanValue(this.intValue == value.intValue);
    }

    @Override
    public final Value isEqual(final DecimalValue value) {
        return new BooleanValue(this.intValue == value.getDecimalValue());
    }

    @Override
    public final Value isGreaterOrEqual(final Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public final Value isGreaterOrEqual(final IntValue value) {
        return new BooleanValue(this.intValue >= value.intValue);
    }

    @Override
    public final Value isGreaterOrEqual(final DecimalValue value) {
        return new BooleanValue(this.intValue >= value.getDecimalValue());
    }

    @Override
    public final Value isGreaterThan(final Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public final Value isGreaterThan(final IntValue value) {
        return new BooleanValue(this.intValue > value.intValue);
    }

    @Override
    public final Value isGreaterThan(final DecimalValue value) {
        return new BooleanValue(this.intValue > value.getDecimalValue());
    }

    @Override
    public final Value isLessOrEqual(final Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public final Value isLessOrEqual(final IntValue value) {
        return new BooleanValue(this.intValue <= value.intValue);
    }

    @Override
    public final Value isLessOrEqual(final DecimalValue value) {
        return new BooleanValue(this.intValue <= value.getDecimalValue());
    }

    @Override
    public final Value isLessThan(final Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public final Value isLessThan(final IntValue value) {
        return new BooleanValue(this.intValue < value.intValue);
    }

    @Override
    public final Value isLessThan(final DecimalValue value) {
        return new BooleanValue(this.intValue < value.getDecimalValue());
    }

    @Override
    public final Value multiply(final Value value) throws EvaluationException {
        return value.multiply(this);
    }

    @Override
    public final Value multiply(final IntValue value) {
        return new IntValue(this.intValue * value.intValue);
    }

    @Override
    public final Value multiply(final DecimalValue value) {
        return new DecimalValue(this.intValue * value.getDecimalValue());
    }

    @Override
    public final Value isNotEqual(final Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public final Value isNotEqual(final IntValue value) {
        return new BooleanValue(this.intValue != value.intValue);
    }

    @Override
    public final Value isNotEqual(final DecimalValue value) {
        return new BooleanValue(this.intValue != value.getDecimalValue());
    }

    @Override
    public final Value subtract(final IntValue value) {
        return new IntValue(this.intValue - value.intValue);
    }

    @Override
    public final Value subtract(final DecimalValue value) {
        return new DecimalValue(this.intValue - value.getDecimalValue());
    }

    @Override
    public final Value negate() {
        return new IntValue(this.intValue * (-1));
    }

    @Override
    public final Value positive() {
        return new IntValue(this.intValue);
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        return NodeType.INTEGER;
    }

    @Override
    public final String toString() {
        return String.valueOf(this.intValue);
    }

    public final IntValue clone() throws CloneNotSupportedException {
        return (IntValue) super.clone();
    }
}
