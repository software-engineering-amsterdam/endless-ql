package org.uva.sea.ql.parser.elements;

public class Neg extends Expr {
    private Expr value;

    public Neg(Expr value) {
        this.value = value;
    }

    public Expr getValue() {
        return value;
    }

    public void setValue(Expr value) {
        this.value = value;
    }
}

