package ql.ast.type;

public class Bool extends Type {

    @Override public String toString() { return "boolean"; }
    @Override public boolean equals(Type t) { return t.isBoolean(); }
    @Override public boolean isBoolean() { return true;  }
    
    // Boolean as unary operand
    @Override public Type negation() { return super.negation(); }
    
    // Boolean as first operand
    @Override public Type and(Type secondOperand) { return secondOperand.logicalAnd(this); }
    @Override public Type or(Type secondOperand) { return secondOperand.logicalOr(this); }
    
    // Boolean as second operand
    @Override public Type logicalAnd(Bool firstOperand) { return new Bool(); }
    @Override public Type logicalOr(Bool firstOperand) { return new Bool(); }
}
