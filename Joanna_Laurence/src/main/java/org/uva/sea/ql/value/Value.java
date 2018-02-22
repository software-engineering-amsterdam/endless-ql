package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;

public abstract class Value {

    public abstract <T> T accept(QLValueEvaluator<T> visitor);

    //add
    public Value add(Value value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(MoneyValue value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(DecimalValue value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(IntValue value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(StringValue value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value add(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //and
    public Value and(Value value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value and(BooleanValue value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value and(DateValue value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value and(DecimalValue value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value and(IntValue value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value and(MoneyValue value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value and(StringValue value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    //divide
    public Value divide(Value value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value divide(BooleanValue value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value divide(DateValue value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value divide(DecimalValue value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value divide(IntValue value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value divide(MoneyValue value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value divide(StringValue value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    //isEqual
    public Value isEqual(Value value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isEqual(BooleanValue value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isEqual(DateValue value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isEqual(DecimalValue value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isEqual(IntValue value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isEqual(MoneyValue value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isEqual(StringValue value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    //isGreaterOrEqual
    public Value isGreaterOrEqual(Value value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(BooleanValue value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(DateValue value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(DecimalValue value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(IntValue value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(MoneyValue value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(StringValue value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    //isGreaterThan
    public Value isGreaterThan(Value value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(BooleanValue value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(DateValue value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(DecimalValue value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(IntValue value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(MoneyValue value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(StringValue value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    //isLessOrEqual
    public Value isLessOrEqual(Value value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(BooleanValue value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(DateValue value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(DecimalValue value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(IntValue value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(MoneyValue value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(StringValue value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    //isLessThan
    public Value isLessThan(Value value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(BooleanValue value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(DateValue value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(DecimalValue value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(IntValue value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(MoneyValue value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(StringValue value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    //multiply
    public Value multiply(Value value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(BooleanValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(DateValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(DecimalValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(IntValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(MoneyValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(StringValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(UndefinedValue undefinedValue) {
        return undefinedValue;
    }


    //negate
    public Value negate() {
        return new ErrorValue("Negate operator cannot be applied here");
    }

    //isNotEqual
    public Value isNotEqual(Value value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(BooleanValue value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(DateValue value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(DecimalValue value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(IntValue value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(MoneyValue value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(StringValue value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    //not
    public Value not() {
        return new ErrorValue("Not operator cannot be applied on here");
    }

    //or
    public Value or(Value value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value or(BooleanValue value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value or(DateValue value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value or(DecimalValue value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value or(IntValue value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value or(MoneyValue value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value or(StringValue value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    //positive
    public Value positive() {
        return new ErrorValue("Positive operator cannot be applied here");
    }

    //subtract
    public Value subtract(Value value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }

    public Value subtract(BooleanValue value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }

    public Value subtract(DateValue value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }

    public Value subtract(DecimalValue value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }

    public Value subtract(IntValue value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }

    public Value subtract(MoneyValue value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }

    public Value subtract(StringValue value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }
}
