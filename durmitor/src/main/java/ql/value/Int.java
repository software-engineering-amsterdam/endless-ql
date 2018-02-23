package ql.value;

import ql.ast.type.Type;

public class Int extends Value<Integer> {

    private int value;
    
    public Int() { 
        this.value = 0;
    }
    
    public Int(String value) { 
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Int();
    }
}
