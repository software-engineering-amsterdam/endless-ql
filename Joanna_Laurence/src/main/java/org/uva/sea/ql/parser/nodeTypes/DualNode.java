package org.uva.sea.ql.parser.nodeTypes;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Traverse;

public abstract class DualNode implements ASTNode {
    private ASTNode lhs;
    private ASTNode rhs;

    public DualNode(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void traverseChildren(Traverse traverse) {
        this.lhs.traverse(traverse);
        this.rhs.traverse(traverse);
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

    /**
     * Traverse over the node
     * @param traverse How to traverse
     */
    public void traverse(Traverse traverse) {
        traverse.doDualNode(this);
    }
}
