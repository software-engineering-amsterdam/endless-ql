package org.uva.qls.ast.Value;

public class ColorValue extends Value {

    private String value;

    public ColorValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
