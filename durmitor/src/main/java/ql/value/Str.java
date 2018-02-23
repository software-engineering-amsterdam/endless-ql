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
    public Type getType() {
        return new ql.ast.type.Str();
    }
}
