package org.uva.sea.ql.parser.nodeTypes;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.Traverse;

public abstract class DualNode extends ASTNode {
    private ASTNode lhs;
    private ASTNode rhs;

    public DualNode(ASTNode lhs, ASTNode rhs) {
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

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.lhs.doTraversal(traverse, traverseType);
        this.rhs.doTraversal(traverse, traverseType);
    }

    /**
     * Traverse over the node
     * @param traverse How to traverseNode
     */
    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doDualNode(this);
    }
}
