package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.NodeType;

public abstract class Value {

    public abstract <T> T accept(QLValueEvaluator<T> visitor);

    public abstract NodeType getType();

    //add
    public Value add(Value value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(MoneyValue value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(DecimalValue value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(IntValue value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(DateValue value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(StringValue value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(BooleanValue value) throws Exception {
        throw new Exception("Add operator cannot be applied here");
    }

    public Value add(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //and
    public Value and(Value value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(BooleanValue value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(DateValue value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(DecimalValue value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(IntValue value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(MoneyValue value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(StringValue value) throws Exception {
        throw new Exception("And operator cannot be applied here");
    }

    public Value and(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //divide
    public Value divide(Value value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(BooleanValue value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(DateValue value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(DecimalValue value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(IntValue value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(MoneyValue value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(StringValue value) throws Exception {
        throw new Exception("Divide operator cannot be applied here");
    }

    public Value divide(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //reverseDivide
    public Value reverseDivide(Value value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(BooleanValue value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(DateValue value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(DecimalValue value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(IntValue value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(MoneyValue value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(StringValue value) throws Exception {
        throw new Exception("Reverse divide operator cannot be applied here");
    }

    public Value reverseDivide(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isEqual
    public Value isEqual(Value value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(BooleanValue value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(DateValue value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(DecimalValue value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(IntValue value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(MoneyValue value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(StringValue value) throws Exception {
        throw new Exception("isEqual operator cannot be applied here");
    }

    public Value isEqual(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isGreaterOrEqual
    public Value isGreaterOrEqual(Value value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(BooleanValue value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(DateValue value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(DecimalValue value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(IntValue value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(MoneyValue value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(StringValue value) throws Exception {
        throw new Exception("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isGreaterThan
    public Value isGreaterThan(Value value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(BooleanValue value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(DateValue value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(DecimalValue value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(IntValue value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(MoneyValue value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(StringValue value) throws Exception {
        throw new Exception("isGreaterThan operator cannot be applied here");
    }

    public Value isGreaterThan(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isLessOrEqual
    public Value isLessOrEqual(Value value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(BooleanValue value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(DateValue value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(DecimalValue value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(IntValue value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(MoneyValue value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(StringValue value) throws Exception {
        throw new Exception("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessOrEqual(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //isLessThan
    public Value isLessThan(Value value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(BooleanValue value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(DateValue value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(DecimalValue value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(IntValue value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(MoneyValue value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(StringValue value) throws Exception {
        throw new Exception("isLessThan operator cannot be applied here");
    }

    public Value isLessThan(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //multiply
    public Value multiply(Value value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(BooleanValue value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(DateValue value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(DecimalValue value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(IntValue value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(MoneyValue value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(StringValue value) throws Exception {
        throw new Exception("Multiply operator cannot be applied here");
    }

    public Value multiply(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //negate
    public Value negate() throws Exception {
        throw new Exception("Negate operator cannot be applied here");
    }

    //isNotEqual
    public Value isNotEqual(Value value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(BooleanValue value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(DateValue value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(DecimalValue value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(IntValue value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(MoneyValue value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(StringValue value) throws Exception {
        throw new Exception("isNotEqual operator cannot be applied here");
    }

    public Value isNotEqual(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //not
    public Value not() throws Exception {
        throw new Exception("Not operator cannot be applied on here");
    }

    //or
    public Value or(Value value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(BooleanValue value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(DateValue value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(DecimalValue value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(IntValue value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(MoneyValue value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(StringValue value) throws Exception {
        throw new Exception("Or operator cannot be applied here");
    }

    public Value or(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //positive
    public Value positive() throws Exception {
        throw new Exception("Positive operator cannot be applied here");
    }

    //subtract
    public Value subtract(Value value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(BooleanValue value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(DateValue value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(DecimalValue value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(IntValue value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(MoneyValue value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(StringValue value) throws Exception {
        throw new Exception("Subtract operator cannot be applied here");
    }

    public Value subtract(UndefinedValue undefinedValue) {
        return undefinedValue;
    }

    //subtract
    public Value reverseSubtract(Value value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(BooleanValue value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(DateValue value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(DecimalValue value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(IntValue value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(MoneyValue value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(StringValue value) throws Exception {
        throw new Exception("Reverse subtract operator cannot be applied here");
    }

    public Value reverseSubtract(UndefinedValue undefinedValue) {
        return undefinedValue;
    }
}
