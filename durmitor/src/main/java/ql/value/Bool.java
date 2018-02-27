package ql.value;

import ql.ast.type.Type;

public class Bool extends Value<Boolean> {

    private boolean value;
    
    public Bool() { 
        this.value = false;
    }
    
    public Bool(String value) { 
        this.value = Boolean.parseBoolean(value);
    }

    public Bool(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }
    
    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Bool();
    }

    // Boolean as unary operand
    
    @Override public Value<?> negation() { return new Bool(!value); }
    
    // Boolean as second operand
    
    @Override public Value<?> logicalAnd(Bool firstOperand) { return new Bool(firstOperand.getValue() && value); }
    @Override public Value<?> logicalOr(Bool firstOperand) { return new Bool(firstOperand.getValue() || value); }
    @Override public Value<?> equalTo(Bool firstOperand) { return new Bool(firstOperand.getValue() == value); }
    @Override public Value<?> notEqualTo(Bool firstOperand) { return new Bool(firstOperand.getValue() != value); }
    
    // Boolean as first operand
    
    @Override public Value<?> and(Value<?> secondOperand) { return secondOperand.logicalAnd(this); }
    @Override public Value<?> or(Value<?> secondOperand) { return secondOperand.logicalOr(this); }
    @Override public Value<?> equal(Value<?> secondOperand) { return secondOperand.equalTo(this); }
    @Override public Value<?> notEqual(Value<?> secondOperand) { return secondOperand.notEqualTo(this); }

}
