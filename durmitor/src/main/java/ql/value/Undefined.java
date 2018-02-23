package ql.value;

import ql.ast.type.Type;

public class Undefined extends Value<String> {

    private final String value;
    
    public Undefined() { 
        this.value = null;
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
    public void setValue(String value) {}

    @Override
    public Type getType() {
        return new ql.ast.type.Undefined();
    }
}
