package Nodes;

import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

/**
 * A default class representing an AST node.
 * It only contains parent functionality.
 */
public abstract class ASTNode {
    private ASTNode parent;

    public ASTNode getParent() { return parent; }

    protected void setParent(ASTNode parent) { this.parent = parent; }

    /**
     * Tells if the node is visible or not.
     * @return A boolean representing this ASTNode's visibility.
     */
    boolean isAvailable() throws SyntaxException, TypeException { return true; }
}
