package org.uva.sea.ql.parser.elements;

public class Not extends Expr {
    private Expr value;

    public Not(Expr value) {
        this.value = value;
    }

    public Expr getValue() {
        return value;
    }

    public void setValue(Expr value) {
        this.value = value;
    }
}
