package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

public abstract class ASTNode {
    /**
     * Traverse over the node
     * @param traverse How to traverse
     */
    public abstract void traverse(Traverse traverse, TraverseType traverseType);

    /**
     * Traverse over the children
     * @param traverse How to traverse
     */
    public void traverseChildren(Traverse traverse, TraverseType traverseType) {

    }

    /**
     *
     * @param traverse
     * @param traverseType
     */
    public void doTraversal(Traverse traverse, TraverseType traverseType) {
        if(traverseType == TraverseType.TOP_DOWN) this.traverse(traverse, traverseType);
        this.traverseChildren(traverse, traverseType);
        if(traverseType == TraverseType.BOTTOM_UP) this.traverse(traverse, traverseType);
    }

    /**
     * Get the node type
     * @return The type
     */
    public abstract Type getType();
}
