package ql.value;

import ql.ast.type.Type;

public class Date extends Value<java.util.Date> {

    private java.util.Date value;
    
    public Date() { 
        this.value = new java.util.Date();
    }
    
    @SuppressWarnings("deprecation")
    public Date(String value) {
        this.value = new java.util.Date(value);
    }

    @Override
    public String toString() {
        return String.format("%td-%tm-%tY", value,value,value);
    }

    @Override
    public java.util.Date getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Date();
    }
}
