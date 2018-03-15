package Nodes;

import Nodes.Operator.Operator;
import Nodes.Operator.Not;
import Nodes.Term.Term;
import QLExceptions.*;

/**
 * Contains a parsed, evaluable expression
 */
public class Expression extends ASTNode {
    private Expression left;
    private Expression right;
    private Operator op;

    /**
     * Creates an empty expression
     * This default is needed for the Term class
     */
    public Expression(){}

    // TODO make NOT operator obsolete
    /**
     * Create an expression with a negated term
     * @param right contains the right side of a Not Expression
     * @param op contains the Not Operator
     */
    public Expression(Expression right, Not op) {
        this.left = null; // Dirty, but Not is a unary operation.
        this.right = right;
        this.op = op;
    }

    /**
     * Creates an expression with left and right expressions, and an operator
     * @param left contains the left Expression of an Expression
     * @param right contains the right Expression of an Expression
     * @param op contains an instance of the abstract Operator class
     */
    public Expression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    /**
     * This sets the parent of this Condition and it's children's parents
     * @param parent this ASTNode's parent
     */
    public void setParents(ASTNode parent) {
        setParent(parent);
        op.setParent(this);
        right.setParents(this);
        if(left != null) // Specific for the Not operator.
            left.setParents(this);
    }

    /**
     * Returns the calculated value of the expression
     * @return The calculated value of the expression
     * @throws SyntaxException when an Operator is not a valid Operator
     * @throws TypeException when Types don't match
     * @throws OtherException when a Variable isn't set yet.
     */
    public Term getTerm() throws SyntaxException, TypeException, OtherException {
        if (op instanceof Not) {
            return op.calculate(null, right.getTerm());
        } else {
            return op.calculate(left.getTerm(), right.getTerm());
        }
    }
}
