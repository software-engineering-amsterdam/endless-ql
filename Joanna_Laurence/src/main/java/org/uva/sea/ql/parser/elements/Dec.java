package org.uva.sea.ql.parser.elements;

public class Dec extends Expr {
    private double value;

    public Dec(double value) {
        this.value = value;
    }

    public double isValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
