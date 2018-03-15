package Nodes.Term;

import Nodes.ASTNode;
import Nodes.Expression;
import QLExceptions.*;

public abstract class Term extends Expression {
    /**
     * For Terms, only set the parent because it is a leaf of a tree.
     */
    @Override
    public void setParents(ASTNode parent) {
        setParent(parent);
    }

    /**
     * Return itself when Expression.getTerm() is called with a Term on one side.
     */
    @Override
    public Term getTerm() throws SyntaxException, OtherException {
        return this;
    }

    @Override
    public String toString() {
        return this.toString().toLowerCase();
    }

    public float getValue(){
        return 0;
    }
}
