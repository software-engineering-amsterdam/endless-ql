package ql.ast.type;

public class Numeric extends Type {

    @Override public String toString() { return "numeric"; }
    @Override public boolean equals(Type t) { return false; }
    @Override public boolean isNumeric() { return true; }

    // Numeric as unary operand
    @Override public Type negative() { return new Numeric(); }
    @Override public Type positive() { return new Numeric(); }
    
    // Numeric as first operand
    @Override public Type multiplyBy(Type secondOperand) { return secondOperand.multiply(this); }
    @Override public Type divideBy(Type secondOperand) { return secondOperand.divide(this); }
    @Override public Type add(Type secondOperand) { return secondOperand.addTo(this); }
    @Override public Type subtract(Type secondOperand) { return secondOperand.subtractFrom(this); }
    @Override public Type less(Type secondOperand) { return secondOperand.greaterThan(this); }
    @Override public Type lessEqual(Type secondOperand) { return secondOperand.greaterThanEqualTo(this); }
    @Override public Type greater(Type secondOperand) { return secondOperand.lessThan(this); }
    @Override public Type greaterEqual(Type secondOperand) { return secondOperand.lessThanEqualTo(this); }

    // Numeric as second operand
    @Override public Type multiply(Numeric firstOperand) { return new Numeric(); }
    @Override public Type multiply(Money firstOperand) { return new Money(); }
    @Override public Type divide(Numeric firstOperand) { return new Numeric(); }
    @Override public Type divide(Money firstOperand) { return new Money(); }
    @Override public Type addTo(Numeric firstOperand) { return new Numeric(); }
    @Override public Type subtractFrom(Numeric firstOperand) { return new Numeric(); }
    @Override public Type greaterThan(Numeric firstOperand) { return new Bool(); }
    @Override public Type greaterThanEqualTo(Numeric firstOperand) { return new Bool(); }
    @Override public Type lessThan(Numeric firstOperand) { return new Bool(); }
    @Override public Type lessThanEqualTo(Numeric firstOperand) { return new Bool(); }
}
