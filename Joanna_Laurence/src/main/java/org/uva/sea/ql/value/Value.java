package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.types.Decimal;
import org.uva.sea.ql.parser.elements.types.Int;
import org.uva.sea.ql.parser.elements.types.Money;

public abstract class Value {

    public abstract <T> T accept(QLValueEvaluator<T> visitor);

    public Value add(Value value) {
        return new ErrorValue("Add operator cannot be applied here");
    }

    public Value and(Value value) {
        return new ErrorValue("And operator cannot be applied here");
    }

    public Value divide(Value value) {
        return new ErrorValue("Divide operator cannot be applied here");
    }

    public Value isEqual(Value value) {
        return new ErrorValue("isEqual operator cannot be applied here");
    }

    public Value isGreaterOrEqual(Value value) {
        return new ErrorValue("isGreaterOrEqual operator cannot be applied here");
    }

    public Value isGreaterThan(Value value) {
        return new ErrorValue("isGreaterThan operator cannot be applied here");
    }

    public Value isLessOrEqual(Value value) {
        return new ErrorValue("isLessOrEqual operator cannot be applied here");
    }

    public Value isLessThan(Value value) {
        return new ErrorValue("isLessThan operator cannot be applied here");
    }

    public Value multiply(Value value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(MoneyValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(DecimalValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value multiply(IntValue value) {
        return new ErrorValue("Multiply operator cannot be applied here");
    }

    public Value negate(){
        return new ErrorValue("Negate operator cannot be applied here");
    };

    public Value isNotEqual(Value value) {
        return new ErrorValue("isNotEqual operator cannot be applied here");
    }

    public Value not(){
        return new ErrorValue("Not operator cannot be applied on here");
    };

    public Value or(Value value) {
        return new ErrorValue("Or operator cannot be applied here");
    }

    public Value positive(){
        return new ErrorValue("Positive operator cannot be applied here");
    };

    public Value subtract(Value value) {
        return new ErrorValue("Subtract operator cannot be applied here");
    }
}
