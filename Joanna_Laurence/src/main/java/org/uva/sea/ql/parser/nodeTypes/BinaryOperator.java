package org.uva.sea.ql.parser.nodeTypes;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitable;
import org.uva.sea.ql.traverse.BaseVisitor;
import org.uva.sea.ql.traverse.Visitor;

public abstract class BinaryOperator extends ASTNode {
    private ASTNode lhs;
    private ASTNode rhs;

    public BinaryOperator(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public ASTNode getLhs() {
        return lhs;
    }

    public ASTNode getRhs() {
        return rhs;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
