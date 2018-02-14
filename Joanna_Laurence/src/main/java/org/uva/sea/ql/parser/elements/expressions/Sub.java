package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;

public class Sub extends ASTNode {
    private ASTNode lhs;
    private ASTNode rhs;

    public Sub(ASTNode lhs, ASTNode rhs) {
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

    public Type getExprType() {
        return lhs.getExprType();
    }

    public boolean checkType() {
        return lhs.getExprType().equals(rhs.getExprType());
    }
}
