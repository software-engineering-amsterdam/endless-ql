package ql.ast.type;

public class Date extends Type {

    @Override public String toString() { return "date"; }
    @Override public boolean equals(Type t) { return t.isDate(); }
    @Override public boolean isDate() { return true; }
    
    // Date as first operand
    @Override public Type less(Type secondOperand) { return secondOperand.greaterThan(this); }
    @Override public Type lessEqual(Type secondOperand) { return secondOperand.greaterThanEqualTo(this); }
    @Override public Type greater(Type secondOperand) { return secondOperand.lessThan(this); }
    @Override public Type greaterEqual(Type secondOperand) { return secondOperand.lessThanEqualTo(this); }
    
    // Date as second operand
    @Override public Type greaterThan(Date firstOperand) { return new Bool(); }
    @Override public Type greaterThanEqualTo(Date firstOperand) { return new Bool(); }
    @Override public Type lessThan(Date firstOperand) { return new Bool(); }
    @Override public Type lessThanEqualTo(Date firstOperand) { return new Bool(); }
}
