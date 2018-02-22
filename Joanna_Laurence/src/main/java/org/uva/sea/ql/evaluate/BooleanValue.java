package org.uva.sea.ql.evaluate;

public class BooleanValue extends Value {
    boolean booleanValue;

    public BooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }
}
