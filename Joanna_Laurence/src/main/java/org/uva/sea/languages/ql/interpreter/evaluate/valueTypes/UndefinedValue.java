package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class UndefinedValue extends Value {

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    //add
    public final Value add(final Value value) {
        return new UndefinedValue();
    }

    public final Value add(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value add(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value add(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value add(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value add(final StringValue value) {
        return new UndefinedValue();
    }

    public final Value add(final BooleanValue value) {
        return new UndefinedValue();
    }

    //and
    public final Value and(final Value value) {
        return new UndefinedValue();
    }

    public final Value and(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value and(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value and(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value and(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value and(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value and(final StringValue value) {
        return new UndefinedValue();
    }

    //divide
    public final Value divide(final Value value) {
        return new UndefinedValue();
    }

    public final Value divide(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value divide(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value divide(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value divide(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value divide(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value divide(final StringValue value) {
        return new UndefinedValue();
    }

    //isEqual
    public final Value isEqual(final Value value) {
        return new UndefinedValue();
    }

    public final Value isEqual(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value isEqual(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value isEqual(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value isEqual(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value isEqual(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value isEqual(final StringValue value) {
        return new UndefinedValue();
    }

    //isGreaterOrEqual
    public final Value isGreaterOrEqual(final Value value) {
        return new UndefinedValue();
    }

    public final Value isGreaterOrEqual(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterOrEqual(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterOrEqual(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterOrEqual(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterOrEqual(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterOrEqual(final StringValue value) {
        return new UndefinedValue();
    }

    //isGreaterThan
    public final Value isGreaterThan(final Value value) {
        return new UndefinedValue();
    }

    public final Value isGreaterThan(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterThan(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterThan(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterThan(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterThan(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value isGreaterThan(final StringValue value) {
        return new UndefinedValue();
    }

    //isLessOrEqual
    public final Value isLessOrEqual(final Value value) {
        return new UndefinedValue();
    }

    public final Value isLessOrEqual(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value isLessOrEqual(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value isLessOrEqual(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value isLessOrEqual(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value isLessOrEqual(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value isLessOrEqual(final StringValue value) {
        return new UndefinedValue();
    }

    //isLessThan
    public final Value isLessThan(final Value value) {
        return new UndefinedValue();
    }

    public final Value isLessThan(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value isLessThan(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value isLessThan(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value isLessThan(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value isLessThan(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value isLessThan(final StringValue value) {
        return new UndefinedValue();
    }

    //multiply
    public final Value multiply(final Value value) {
        return new UndefinedValue();
    }

    public final Value multiply(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value multiply(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value multiply(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value multiply(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value multiply(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value multiply(final StringValue value) {
        return new UndefinedValue();
    }

    //negate
    public final Value negate() {
        return new UndefinedValue();
    }

    //isNotEqual
    public final Value isNotEqual(final Value value) {
        return new UndefinedValue();
    }

    public final Value isNotEqual(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value isNotEqual(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value isNotEqual(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value isNotEqual(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value isNotEqual(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value isNotEqual(final StringValue value) {
        return new UndefinedValue();
    }

    //not
    public final Value not() {
        return new UndefinedValue();
    }

    //or
    public final Value or(final Value value) {
        return new UndefinedValue();
    }

    public final Value or(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value or(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value or(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value or(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value or(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value or(final StringValue value) {
        return new UndefinedValue();
    }

    //positive
    public final Value positive() {
        return new UndefinedValue();
    }

    //subtract
    public final Value subtract(final Value value) {
        return new UndefinedValue();
    }

    public final Value subtract(final BooleanValue value) {
        return new UndefinedValue();
    }

    public final Value subtract(final DateValue value) {
        return new UndefinedValue();
    }

    public final Value subtract(final DecimalValue value) {
        return new UndefinedValue();
    }

    public final Value subtract(final IntValue value) {
        return new UndefinedValue();
    }

    public final Value subtract(final MoneyValue value) {
        return new UndefinedValue();
    }

    public final Value subtract(final StringValue value) {
        return new UndefinedValue();
    }

    @Override
    public final NodeType getType() {
        return NodeType.UNKNOWN;
    }

    public final UndefinedValue clone() throws CloneNotSupportedException {
        return (UndefinedValue) super.clone();
    }
}
