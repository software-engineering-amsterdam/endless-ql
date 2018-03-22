package Nodes.Term;

import Nodes.ASTNode;
import Nodes.Expression;
import Nodes.Type;
import QLExceptions.*;

public abstract class Term extends Expression {
    /**
     * For Terms, only set the parent because it is a leaf of a tree
     */
    @Override
    public void setParents(ASTNode parent) {
        setParent(parent);
    }

    /**
     * Return itself when Expression.getTerm() is called with a Term on one side
     * It can throw a SyntaxExpression or OtherException when the override in Variable is called.
     */
    @Override
    public Term getTerm() throws SyntaxException, OtherException {
        return this;
    }

    /**
     * Default implementations for every Term we allow in expressions.
     * @return the boolean, float or string, depending on which method is called.
     * @throws TypeException when the wrong method is called for a certain Term.
     */
    public boolean getBoolean() throws TypeException { throw new TypeException(this); }
    public float getFloat() throws TypeException { throw new TypeException(this); }
    public String getString() throws TypeException { throw new TypeException(this); }

    /**
     * Default implementation to get the corresponding Type enum for a Term.
     * @return A Type enum corresponding to the Term.
     * @throws SyntaxException when an invalid Term is used.
     */
    public Type getType() throws SyntaxException {
        // Default implementation is to throw a TypeException.
        throw new SyntaxException("Invalid type", this);
    }

    /**
     * Every Term needs to implement this isEqual method so we can check equality except Variable, as those will never be passed to an Operator.
     * @return true or false, based on the equal operator.
     * @throws TypeException when the Type aren't equal.
     */
    public boolean isEqual(Term other) throws TypeException {
        // Default implementation is to throw a TypeException.
        throw new TypeException();
    }
}
