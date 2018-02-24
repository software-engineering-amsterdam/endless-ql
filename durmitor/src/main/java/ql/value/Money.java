package ql.value;

import ql.ast.type.Type;

public class Money extends Value<String> {

    private String value;
    
    public Money() { 
        this.value = "0.00";
    }
    
    public Money(String value) { 
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
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
        return new ql.ast.type.Money();
    }
}
