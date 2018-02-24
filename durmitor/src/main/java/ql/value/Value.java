package ql.value;

import ql.ast.type.Date;
import ql.ast.type.Money;
import ql.ast.type.Numeric;
import ql.ast.type.Str;
import ql.ast.type.Type;

public abstract class Value<T> {
    
    public abstract Type getType();
    public abstract T getValue();
    public abstract void setValue(T value);
    
    public Value<?> equal(Value<?> secondOperand) { return new Bool(); }
    public Value<?> notEqual(Value<?> secondOperand) { return new Bool(); }

    public Value<?> negation() { return new Undefined(); }
    public Value<?> negative() { return new Undefined(); }
    public Value<?> positive() { return new Undefined(); }
    public Value<?> multiplyBy(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> divideBy(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> add(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> subtract(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> less(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> lessEqual(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> greaterEqual(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> greater(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> and(Value<?> secondOperand) { return new Undefined(); }
    public Value<?> or(Value<?> secondOperand) { return new Undefined(); }

    public Value<?> multiply(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> multiply(Numeric firstOperand) { return new Undefined(); }
    public Value<?> multiply(Money firstOperand) { return new Undefined(); }
    
    public Value<?> divide(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> divide(Numeric firstOperand) { return new Undefined(); }
    public Value<?> divide(Money firstOperand) { return new Undefined(); }

    public Value<?> addTo(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> addTo(Numeric firstOperand) { return new Undefined(); }
    public Value<?> addTo(Money firstOperand) { return new Undefined(); }
    public Value<?> addTo(Str firstOperand) { return new Undefined(); }
    
    public Value<?> subtractFrom(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> subtractFrom(Numeric firstOperand) { return new Undefined(); }
    public Value<?> subtractFrom(Money firstOperand) { return new Undefined(); }

    public Value<?> lessThan(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> lessThan(Numeric firstOperand) { return new Undefined(); }
    public Value<?> lessThan(Money firstOperand) { return new Undefined(); }
    public Value<?> lessThan(Date firstOperand) { return new Undefined(); }

    public Value<?> lessThanEqualTo(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> lessThanEqualTo(Numeric firstOperand) { return new Undefined(); }
    public Value<?> lessThanEqualTo(Money firstOperand) { return new Undefined(); }
    public Value<?> lessThanEqualTo(Date firstOperand) { return new Undefined(); }

    public Value<?> greaterThan(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> greaterThan(Numeric firstOperand) { return new Undefined(); }
    public Value<?> greaterThan(Money firstOperand) { return new Undefined(); }
    public Value<?> greaterThan(Date firstOperand) { return new Undefined(); }

    public Value<?> greaterThanEqualTo(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> greaterThanEqualTo(Numeric firstOperand) { return new Undefined(); }
    public Value<?> greaterThanEqualTo(Money firstOperand) { return new Undefined(); }
    public Value<?> greaterThanEqualTo(Date firstOperand) { return new Undefined(); }

    public Value<?> logicalAnd(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> logicalAnd(Bool firstOperand) { return new Undefined(); }

    public Value<?> logicalOr(Value<?> firstOperand) { return new Undefined(); }
    public Value<?> logicalOr(Bool firstOperand) { return new Undefined(); }
}
