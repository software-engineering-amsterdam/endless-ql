package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;

public class Div extends ASTNode {
    private ASTNode lhs;
    private ASTNode rhs;

    public Div(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public ASTNode getLhs() {
        return lhs;
    }

    public void setLhs(ASTNode lhs) {
        this.lhs = lhs;
    }

    public ASTNode getRhs() {
        return rhs;
    }

    public void setRhs(ASTNode rhs) {
        this.rhs = rhs;
    }
}
