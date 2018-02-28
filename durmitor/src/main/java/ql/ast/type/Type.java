package ql.ast.type;

import ql.ast.QLNode;
import ql.evaluator.Operations;
import ql.evaluator.value.Value;
import ql.visitors.interfaces.TypeVisitable;

public abstract class Type extends QLNode implements TypeVisitable, Operations<Type> {

    public abstract String toString();

    public abstract Value<?> toValue();

    public abstract boolean equals(Type t);

    public boolean isBoolean() {
        return false;
    }

    public boolean isInteger() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isDecimal() {
        return false;
    }

    public boolean isDate() {
        return false;
    }

    public boolean isMoney() {
        return false;
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isUndefined() {
        return false;
    }
    
    public Type negation() {
        return new Undefined(); 
    }
    
    public Type negative() {
        return new Undefined(); 
    }
    
    public Type positive() {
        return new Undefined(); 
    }
    
    public Type and(Type secondOperand) {
        return new Undefined(); 
    }
    
    public Type or(Type secondOperand) {
        return new Undefined(); 
    }
    
    public Type add(Type secondOperand) {
        return new Undefined(); 
    }
    
    public Type subtract(Type secondOperand) {
        return new Undefined(); 
    }
    
    public Type multiply(Type secondOperand) {
        return new Undefined(); 
    }
    
    public Type divide(Type secondOperand) {
        return new Undefined(); 
    }
    
    @Override
    public Type less(Type secondOperand) {
        return new Undefined(); 
    }

    @Override
    public Type lessEqual(Type secondOperand) {
        return new Undefined(); 
    }

    @Override
    public Type greater(Type secondOperand) {
        return new Undefined(); 
    }

    @Override
    public Type greaterEqual(Type secondOperand) {
        return new Undefined(); 
    }

    @Override
    public Type equal(Type secondOperand) {
        return new Undefined(); 
    }

    @Override
    public Type notEqual(Type secondOperand) {
        return new Undefined(); 
    }

    public abstract Value<?> parse(Value<?> value);
}
