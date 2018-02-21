package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

public abstract class ASTNode {

    private int line;
    private int column;

    /**
     * Traverse over the node
     * @param traverse How to traverseNode
     */
    public abstract void traverseNode(Traverse traverse, TraverseType traverseType);

    /**
     * Traverse over the children
     * @param traverse How to traverseNode
     */
    public void traverseChildren(Traverse traverse, TraverseType traverseType) {

    }

    /**
     *
     * @param traverse
     * @param traverseType
     */
    public void doTraversal(Traverse traverse, TraverseType traverseType) {
        if(traverseType == TraverseType.TOP_DOWN) this.traverseNode(traverse, traverseType);
        this.traverseChildren(traverse, traverseType);
        if(traverseType == TraverseType.BOTTOM_UP) this.traverseNode(traverse, traverseType);
    }

    /**
     * Get the node type
     * @return The type
     */
    public abstract Type getType();

    public int getLine(){
        return this.line;
    }

    public int getColumn(){
        return this.column;
    }

    public void setLocation(int line, int column){
        this.line = line;
        this.column = column;
    }
}
