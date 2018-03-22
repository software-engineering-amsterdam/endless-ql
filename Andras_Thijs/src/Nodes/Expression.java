package Nodes;

import Nodes.Operator.Operator;
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
    protected Expression() {}

    /**
     * Create an unary Expression (only used for Not).
     * @param right contains the right side of a Not Expression.
     * @param op contains the Not Operator.
     */
    public Expression(Expression right, Operator op) {
        this.left = right; // Dirty, but Not is a unary operation.
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
        left.setParents(this);
    }

    /**
     * Evaluates the expression by getting passing the evaluated left and right side of this Expression to it's Operator.
     * @return The calculated value of the expression.
     * @throws SyntaxException when an Operator is not a valid Operator.
     * @throws TypeException when Types don't match.
     * @throws OtherException when a Variable isn't set yet.
     */
    public Term getTerm() throws SyntaxException, TypeException, OtherException {
        return op.calculate(left.getTerm(), right.getTerm());
    }
}
