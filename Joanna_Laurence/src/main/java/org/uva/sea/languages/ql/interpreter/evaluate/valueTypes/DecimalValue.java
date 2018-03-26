package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;


public class DecimalValue extends Value {

    private final double decimalValue;

    public DecimalValue(final String value) {
        this.decimalValue = Double.parseDouble(value);
    }

    public DecimalValue(final double decimalValue) {

        this.decimalValue = decimalValue;
    }

    public final double getDecimalValue() {
        return this.decimalValue;
    }

    @Override
    public final Value add(final Value value) throws EvaluationException {
        return value.add(this);
    }

    @Override
    public final Value add(final IntValue value) {
        return new DecimalValue(this.decimalValue + value.getIntValue());
    }

    @Override
    public final Value add(final DecimalValue value) {
        return new DecimalValue(this.decimalValue + value.decimalValue);
    }

    @Override
    public final Value divide(final Value value) throws EvaluationException {
        return value.divide(this);
    }

    @Override
    public final Value divide(final IntValue value) throws EvaluationException {
        if (value.getIntValue() == 0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue(this.decimalValue / value.getIntValue());
    }

    @Override
    public final Value divide(final DecimalValue value) throws EvaluationException {
        if (value.decimalValue == 0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue(this.decimalValue / value.decimalValue);
    }

    @Override
    public final Value isEqual(final Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public final Value isEqual(final IntValue value) {
        return new BooleanValue(this.decimalValue == value.getIntValue());
    }

    @Override
    public final Value isEqual(final DecimalValue value) {
        return new BooleanValue(this.decimalValue == value.decimalValue);
    }

    @Override
    public final Value isGreaterOrEqual(final Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public final Value isGreaterOrEqual(final IntValue value) {
        return new BooleanValue(this.decimalValue >= value.getIntValue());
    }

    @Override
    public final Value isGreaterOrEqual(final DecimalValue value) {
        return new BooleanValue(this.decimalValue >= value.decimalValue);
    }

    @Override
    public final Value isGreaterThan(final Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public final Value isGreaterThan(final IntValue value) {
        return new BooleanValue(this.decimalValue > value.getIntValue());
    }

    @Override
    public final Value isGreaterThan(final DecimalValue value) {
        return new BooleanValue(this.decimalValue > value.decimalValue);
    }

    @Override
    public final Value isLessOrEqual(final Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public final Value isLessOrEqual(final IntValue value) {
        return new BooleanValue(this.decimalValue <= value.getIntValue());
    }

    @Override
    public final Value isLessOrEqual(final DecimalValue value) {
        return new BooleanValue(this.decimalValue <= value.decimalValue);
    }

    @Override
    public final Value isLessThan(final Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public final Value isLessThan(final IntValue value) {
        return new BooleanValue(this.decimalValue < value.getIntValue());
    }

    @Override
    public final Value isLessThan(final DecimalValue value) {
        return new BooleanValue(this.decimalValue < value.decimalValue);
    }

    @Override
    public final Value multiply(final Value value) throws EvaluationException {
        return value.multiply(this);
    }

    @Override
    public final Value multiply(final IntValue value) {
        return new DecimalValue(this.decimalValue * value.getIntValue());
    }

    @Override
    public final Value multiply(final DecimalValue value) {
        return new DecimalValue(this.decimalValue * value.decimalValue);
    }

    @Override
    public final Value isNotEqual(final Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public final Value isNotEqual(final IntValue value) {
        return new BooleanValue(this.decimalValue != value.getIntValue());
    }

    @Override
    public final Value isNotEqual(final DecimalValue value) {
        return new BooleanValue(this.decimalValue != value.decimalValue);
    }

    @Override
    public final Value subtract(final Value value) throws EvaluationException {
        return value.subtract(this);
    }

    @Override
    public final Value subtract(final IntValue value) {
        return new DecimalValue(this.decimalValue - value.getIntValue());
    }

    @Override
    public final Value subtract(final DecimalValue value) {
        return new DecimalValue(this.decimalValue - value.decimalValue);
    }

    @Override
    public final Value negate() {
        return new DecimalValue(this.decimalValue * (-1));
    }

    @Override
    public final Value positive() {
        return new DecimalValue(this.decimalValue);
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        return NodeType.DECIMAL;
    }

    @Override
    public final String toString() {
        return String.valueOf(this.decimalValue);
    }

    public final DecimalValue clone() throws CloneNotSupportedException {
        return (DecimalValue) super.clone();
    }
}
