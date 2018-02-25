package ql.value;

import ql.ast.type.Type;

public class Str extends Value<String> {

    private String value;

    public Str() {
        this.value = "";
    }

    public Str(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Str();
    }

    // String as second operand
    
    @Override
    public Value<?> addTo(Str firstOperand) {
        return new Str(firstOperand.getValue().concat(value));
    }
    
    @Override
    public Value<?> equalTo(Str firstOperand) {
        return new Bool(firstOperand.getValue().equals(value));
    }
    
    // String as first operand
    
    @Override
    public Value<?> add(Value<?> secondOperand) {
        return secondOperand.addTo(this);
    }
    
    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.equalTo(this);
    }
}
