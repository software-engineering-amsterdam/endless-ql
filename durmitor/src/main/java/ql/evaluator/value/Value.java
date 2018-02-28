package ql.evaluator.value;

import ql.ast.type.Type;
import ql.evaluator.Operations;
import ql.visitors.interfaces.ValueVisitable;

public abstract class Value<T> implements ValueVisitable, Operations<Value<?>> {

    public abstract Type getType();

    public abstract T getValue();
    
    public Value<?> negation() {
        return new Undefined();
    }
    
    public Value<?> negative() {
        return new Undefined();
    }
    
    public Value<?> positive() {
        return new Undefined();
    }
    
    public Value<?> add(Value<?> secondOperand) {
        return new Undefined();
    }
    
    public Value<?> subtract(Value<?> secondOperand) {
        return new Undefined();
    }
    
    public Value<?> multiply(Value<?> secondOperand) {
        return new Undefined();
    }
    
    public Value<?> divide(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> less(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> lessEqual(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> greater(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> greaterEqual(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> equal(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> notEqual(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> and(Value<?> secondOperand) {
        return new Undefined();
    }

    public Value<?> or(Value<?> secondOperand) {
        return new Undefined();
    }
}
