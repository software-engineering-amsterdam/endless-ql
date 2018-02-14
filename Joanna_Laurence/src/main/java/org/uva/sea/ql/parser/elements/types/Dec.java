package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.Expr;

public class Dec extends Expr {
    private double value;

    public Dec(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
