package ql.ast.type;

public class Money extends Type {

    @Override public String toString() { return "money"; }
    @Override public boolean equals(Type t) { return t.isMoney(); }
    @Override public boolean isMoney() { return true; }

    // Money as unary operand
    @Override public Type negative() { return new Money(); }
    @Override public Type positive() { return new Money(); }
    
    // Money as first operand
    @Override public Type multiplyBy(Type secondOperand) { return secondOperand.multiply(this); }
    @Override public Type divideBy(Type secondOperand) { return secondOperand.divide(this); }
    @Override public Type add(Type secondOperand) { return secondOperand.addTo(this); }
    @Override public Type subtract(Type secondOperand) { return secondOperand.subtractFrom(this); }
    @Override public Type less(Type secondOperand) { return secondOperand.greaterThan(this); }
    @Override public Type lessEqual(Type secondOperand) { return secondOperand.greaterThanEqualTo(this); }
    @Override public Type greater(Type secondOperand) { return secondOperand.lessThan(this); }
    @Override public Type greaterEqual(Type secondOperand) { return secondOperand.lessThanEqualTo(this); }
    
    // Money as second operand
    @Override public Type multiply(Numeric firstOperand) { return new Money(); }
    @Override public Type addTo(Money firstOperand) { return new Money(); }
    @Override public Type subtractFrom(Money firstOperand) { return new Money(); }
    @Override public Type greaterThan(Type secondOperand) { return new Bool(); }
    @Override public Type greaterThanEqualTo(Type secondOperand) { return new Bool(); }
    @Override public Type lessThan(Type secondOperand) { return new Bool(); }
    @Override public Type lessThanEqualTo(Type secondOperand) { return new Bool(); }
}
