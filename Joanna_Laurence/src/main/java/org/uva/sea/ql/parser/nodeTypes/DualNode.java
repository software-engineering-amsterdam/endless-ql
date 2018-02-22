package org.uva.sea.ql.parser.nodeTypes;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitable;
import org.uva.sea.ql.traverse.BaseVisitor;

public abstract class DualNode extends ASTNode implements Visitable {
    private ASTNode lhs;
    private ASTNode rhs;

    public DualNode(ASTNode lhs, ASTNode rhs) {
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
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }
}
