package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.Expr;

public class Num extends Expr {
    private int value;

    public Num(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
