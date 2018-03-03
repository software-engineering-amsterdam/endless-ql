package org.uva.sea.ql.evaluate.valueTypes;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.visitor.BaseValueVisitor;

public class UndefinedValue extends Value {

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    //add
    public Value add(Value value) {
        return new UndefinedValue();
    }

    public Value add(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value add(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value add(IntValue value) {
        return new UndefinedValue();
    }

    public Value add(DateValue value) {
        return new UndefinedValue();
    }

    public Value add(StringValue value) {
        return new UndefinedValue();
    }

    public Value add(BooleanValue value) {
        return new UndefinedValue();
    }

    //and
    public Value and(Value value) {
        return new UndefinedValue();
    }

    public Value and(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value and(DateValue value) {
        return new UndefinedValue();
    }

    public Value and(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value and(IntValue value) {
        return new UndefinedValue();
    }

    public Value and(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value and(StringValue value) {
        return new UndefinedValue();
    }

    //divide
    public Value divide(Value value) {
        return new UndefinedValue();
    }

    public Value divide(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value divide(DateValue value) {
        return new UndefinedValue();
    }

    public Value divide(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value divide(IntValue value) {
        return new UndefinedValue();
    }

    public Value divide(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value divide(StringValue value) {
        return new UndefinedValue();
    }

    //isEqual
    public Value isEqual(Value value) {
        return new UndefinedValue();
    }

    public Value isEqual(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value isEqual(DateValue value) {
        return new UndefinedValue();
    }

    public Value isEqual(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value isEqual(IntValue value) {
        return new UndefinedValue();
    }

    public Value isEqual(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value isEqual(StringValue value) {
        return new UndefinedValue();
    }

    //isGreaterOrEqual
    public Value isGreaterOrEqual(Value value) {
        return new UndefinedValue();
    }

    public Value isGreaterOrEqual(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterOrEqual(DateValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterOrEqual(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterOrEqual(IntValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterOrEqual(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterOrEqual(StringValue value) {
        return new UndefinedValue();
    }

    //isGreaterThan
    public Value isGreaterThan(Value value) {
        return new UndefinedValue();
    }

    public Value isGreaterThan(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterThan(DateValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterThan(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterThan(IntValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterThan(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value isGreaterThan(StringValue value) {
        return new UndefinedValue();
    }

    //isLessOrEqual
    public Value isLessOrEqual(Value value) {
        return new UndefinedValue();
    }

    public Value isLessOrEqual(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value isLessOrEqual(DateValue value) {
        return new UndefinedValue();
    }

    public Value isLessOrEqual(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value isLessOrEqual(IntValue value) {
        return new UndefinedValue();
    }

    public Value isLessOrEqual(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value isLessOrEqual(StringValue value) {
        return new UndefinedValue();
    }

    //isLessThan
    public Value isLessThan(Value value) {
        return new UndefinedValue();
    }

    public Value isLessThan(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value isLessThan(DateValue value) {
        return new UndefinedValue();
    }

    public Value isLessThan(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value isLessThan(IntValue value) {
        return new UndefinedValue();
    }

    public Value isLessThan(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value isLessThan(StringValue value) {
        return new UndefinedValue();
    }

    //multiply
    public Value multiply(Value value) {
        return new UndefinedValue();
    }

    public Value multiply(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value multiply(DateValue value) {
        return new UndefinedValue();
    }

    public Value multiply(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value multiply(IntValue value) {
        return new UndefinedValue();
    }

    public Value multiply(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value multiply(StringValue value) {
        return new UndefinedValue();
    }

    //negate
    public Value negate() {
        return new UndefinedValue();
    }

    //isNotEqual
    public Value isNotEqual(Value value) {
        return new UndefinedValue();
    }

    public Value isNotEqual(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value isNotEqual(DateValue value) {
        return new UndefinedValue();
    }

    public Value isNotEqual(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value isNotEqual(IntValue value) {
        return new UndefinedValue();
    }

    public Value isNotEqual(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value isNotEqual(StringValue value) {
        return new UndefinedValue();
    }

    //not
    public Value not() {
        return new UndefinedValue();
    }

    //or
    public Value or(Value value) {
        return new UndefinedValue();
    }

    public Value or(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value or(DateValue value) {
        return new UndefinedValue();
    }

    public Value or(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value or(IntValue value) {
        return new UndefinedValue();
    }

    public Value or(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value or(StringValue value) {
        return new UndefinedValue();
    }

    //positive
    public Value positive() {
        return new UndefinedValue();
    }

    //subtract
    public Value subtract(Value value) {
        return new UndefinedValue();
    }

    public Value subtract(BooleanValue value) {
        return new UndefinedValue();
    }

    public Value subtract(DateValue value) {
        return new UndefinedValue();
    }

    public Value subtract(DecimalValue value) {
        return new UndefinedValue();
    }

    public Value subtract(IntValue value) {
        return new UndefinedValue();
    }

    public Value subtract(MoneyValue value) {
        return new UndefinedValue();
    }

    public Value subtract(StringValue value) {
        return new UndefinedValue();
    }

    @Override
    public NodeType getType() {
        return NodeType.UNKNOWN;
    }
}
