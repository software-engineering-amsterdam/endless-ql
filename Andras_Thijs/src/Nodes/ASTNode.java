package Nodes;

import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

public abstract class ASTNode {
    private ASTNode parent;

    public ASTNode getParent() {
        return parent;
    }

    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    public boolean isAvailable() throws SyntaxException, TypeException {
        return true;
    }
}
