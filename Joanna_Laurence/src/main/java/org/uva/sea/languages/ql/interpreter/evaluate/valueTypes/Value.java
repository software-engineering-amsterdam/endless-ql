package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public abstract class Value implements Cloneable {

    public abstract <T> T accept(BaseValueVisitor<T> visitor);

    public abstract NodeType getType();

    //add
    public Value add(final Value value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    Value add(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    Value add(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    Value add(final IntValue value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    public Value add(final DateValue value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    public Value add(final StringValue value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    public Value add(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("Add operator cannot be applied here");
    }

    public final Value add(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //and
    public Value and(final Value value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    Value and(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    public Value and(final DateValue value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    public Value and(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    public Value and(final IntValue value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    public Value and(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    public Value and(final StringValue value) throws EvaluationException {
        throw new EvaluationException("And operator cannot be applied here");
    }

    public final Value and(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //divide
    public Value divide(final Value value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    public Value divide(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    public Value divide(final DateValue value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    Value divide(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    Value divide(final IntValue value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    Value divide(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    public Value divide(final StringValue value) throws EvaluationException {
        throw new EvaluationException("Divide operator cannot be applied here");
    }

    public final Value divide(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isEqual
    public Value isEqual(final Value value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    Value isEqual(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    Value isEqual(final DateValue value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    Value isEqual(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    Value isEqual(final IntValue value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    Value isEqual(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    Value isEqual(final StringValue value) throws EvaluationException {
        throw new EvaluationException("isEqual operator cannot be applied here");
    }

    public final Value isEqual(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isGreaterOrEqual
    public Value isGreaterOrEqual(final Value value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    Value isGreaterOrEqual(final DateValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    Value isGreaterOrEqual(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    Value isGreaterOrEqual(final IntValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    Value isGreaterOrEqual(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(final StringValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterOrEqual operator cannot be applied here");
    }

    public final Value isGreaterOrEqual(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isGreaterThan
    public Value isGreaterThan(final Value value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    Value isGreaterThan(final DateValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    Value isGreaterThan(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    Value isGreaterThan(final IntValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    Value isGreaterThan(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(final StringValue value) throws EvaluationException {
        throw new EvaluationException("isGreaterThan operator cannot be applied here");
    }

    public final Value isGreaterThan(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isLessOrEqual
    public Value isLessOrEqual(final Value value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    Value isLessOrEqual(final DateValue value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    Value isLessOrEqual(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    Value isLessOrEqual(final IntValue value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    Value isLessOrEqual(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(final StringValue value) throws EvaluationException {
        throw new EvaluationException("isLessOrEqual operator cannot be applied here");
    }

    public final Value isLessOrEqual(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isLessThan
    public Value isLessThan(final Value value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    Value isLessThan(final DateValue value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    Value isLessThan(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    Value isLessThan(final IntValue value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    Value isLessThan(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(final StringValue value) throws EvaluationException {
        throw new EvaluationException("isLessThan operator cannot be applied here");
    }

    public final Value isLessThan(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //multiply
    public Value multiply(final Value value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    public Value multiply(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    public Value multiply(final DateValue value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    Value multiply(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    Value multiply(final IntValue value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    Value multiply(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    public Value multiply(final StringValue value) throws EvaluationException {
        throw new EvaluationException("Multiply operator cannot be applied here");
    }

    public final Value multiply(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //negate
    public Value negate() throws EvaluationException {
        throw new EvaluationException("Negate operator cannot be applied here");
    }

    //isNotEqual
    public Value isNotEqual(final Value value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    Value isNotEqual(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    Value isNotEqual(final DateValue value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    Value isNotEqual(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    Value isNotEqual(final IntValue value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    Value isNotEqual(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    Value isNotEqual(final StringValue value) throws EvaluationException {
        throw new EvaluationException("isNotEqual operator cannot be applied here");
    }

    public final Value isNotEqual(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //not
    public Value not() throws EvaluationException {
        throw new EvaluationException("Not operator cannot be applied on here");
    }

    //or
    public Value or(final Value value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    Value or(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    public Value or(final DateValue value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    public Value or(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    public Value or(final IntValue value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    public Value or(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    public Value or(final StringValue value) throws EvaluationException {
        throw new EvaluationException("Or operator cannot be applied here");
    }

    public final Value or(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //positive
    public Value positive() throws EvaluationException {
        throw new EvaluationException("Positive operator cannot be applied here");
    }

    //subtract
    public Value subtract(final Value value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    public Value subtract(final BooleanValue value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    public Value subtract(final DateValue value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    Value subtract(final DecimalValue value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    public Value subtract(final IntValue value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    public Value subtract(final MoneyValue value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    public Value subtract(final StringValue value) throws EvaluationException {
        throw new EvaluationException("Subtract operator cannot be applied here");
    }

    public final Value subtract(final UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    public Value clone() throws CloneNotSupportedException {
        return (Value) super.clone();
    }
}
