package ql.ast.type;

import ql.ast.QLNode;

public abstract class Type extends QLNode {

    public abstract String toString();
    public abstract boolean equals(Type t);
    
    public abstract void setName(String name);
    public abstract String getName();
    public abstract void setValue(String value);
    
    public boolean isBoolean() { return false; }
    public boolean isInteger() { return false; }
    public boolean isString() { return false; }
    public boolean isDecimal() { return false; }
    public boolean isDate() { return false; }
    public boolean isMoney() { return false; }
    public boolean isNumeric() { return false; }
    public boolean isUndefined() { return false; }
    
    public Type equal(Type secondOperand) { return new Bool(); }
    public Type notEqual(Type secondOperand) { return new Bool(); }

    public Type negation() { return new Undefined(); }
    public Type negative() { return new Undefined(); }
    public Type positive() { return new Undefined(); }
    public Type multiplyBy(Type secondOperand) { return new Undefined(); }
    public Type divideBy(Type secondOperand) { return new Undefined(); }
    public Type add(Type secondOperand) { return new Undefined(); }
    public Type subtract(Type secondOperand) { return new Undefined(); }
    public Type less(Type secondOperand) { return new Undefined(); }
    public Type lessEqual(Type secondOperand) { return new Undefined(); }
    public Type greaterEqual(Type secondOperand) { return new Undefined(); }
    public Type greater(Type secondOperand) { return new Undefined(); }
    public Type and(Type secondOperand) { return new Undefined(); }
    public Type or(Type secondOperand) { return new Undefined(); }

    public Type multiply(Type firstOperand) { return new Undefined(); }
    public Type multiply(Numeric firstOperand) { return new Undefined(); }
    public Type multiply(Money firstOperand) { return new Undefined(); }
    
    public Type divide(Type firstOperand) { return new Undefined(); }
    public Type divide(Numeric firstOperand) { return new Undefined(); }
    public Type divide(Money firstOperand) { return new Undefined(); }

    public Type addTo(Type firstOperand) { return new Undefined(); }
    public Type addTo(Numeric firstOperand) { return new Undefined(); }
    public Type addTo(Money firstOperand) { return new Undefined(); }
    public Type addTo(Str firstOperand) { return new Undefined(); }
    
    public Type subtractFrom(Type firstOperand) { return new Undefined(); }
    public Type subtractFrom(Numeric firstOperand) { return new Undefined(); }
    public Type subtractFrom(Money firstOperand) { return new Undefined(); }

    public Type lessThan(Type firstOperand) { return new Undefined(); }
    public Type lessThan(Numeric firstOperand) { return new Undefined(); }
    public Type lessThan(Money firstOperand) { return new Undefined(); }
    public Type lessThan(Date firstOperand) { return new Undefined(); }

    public Type lessThanEqualTo(Type firstOperand) { return new Undefined(); }
    public Type lessThanEqualTo(Numeric firstOperand) { return new Undefined(); }
    public Type lessThanEqualTo(Money firstOperand) { return new Undefined(); }
    public Type lessThanEqualTo(Date firstOperand) { return new Undefined(); }

    public Type greaterThan(Type firstOperand) { return new Undefined(); }
    public Type greaterThan(Numeric firstOperand) { return new Undefined(); }
    public Type greaterThan(Money firstOperand) { return new Undefined(); }
    public Type greaterThan(Date firstOperand) { return new Undefined(); }

    public Type greaterThanEqualTo(Type firstOperand) { return new Undefined(); }
    public Type greaterThanEqualTo(Numeric firstOperand) { return new Undefined(); }
    public Type greaterThanEqualTo(Money firstOperand) { return new Undefined(); }
    public Type greaterThanEqualTo(Date firstOperand) { return new Undefined(); }

    public Type logicalAnd(Type firstOperand) { return new Undefined(); }
    public Type logicalAnd(Bool firstOperand) { return new Undefined(); }

    public Type logicalOr(Type firstOperand) { return new Undefined(); }
    public Type logicalOr(Bool firstOperand) { return new Undefined(); }
}
