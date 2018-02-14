package org.uva.sea.ql.parser.elements;

public class GThan extends Expr {
    private Expr lhs;
    private Expr rhs;

    public GThan(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expr getLhs() {
        return lhs;
    }

    public void setLhs(Expr lhs) {
        this.lhs = lhs;
    }

    public Expr getRhs() {
        return rhs;
    }

    public void setRhs(Expr rhs) {
        this.rhs = rhs;
    }
}
