package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.Expr;

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

