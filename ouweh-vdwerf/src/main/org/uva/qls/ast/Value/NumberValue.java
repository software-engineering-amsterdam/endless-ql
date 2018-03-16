package org.uva.qls.ast.Value;

public class NumberValue extends Value {

    private String value;

    public NumberValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
