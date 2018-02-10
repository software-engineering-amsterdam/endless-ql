package org.uva.sea.ql.parser.elements;

public class Bool extends Expr {
    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
