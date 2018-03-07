package Nodes;

import Nodes.Operator.Operator;
import Nodes.Operator.Not;
import Nodes.Term.Term;

/**
 * Contains a parsed, evaluable expression
 */
public class Expression extends ASTNode {
    private Expression left;
    private Expression right;
    private Operator op;
    private Term term;

    /**
     * Creates an empty expression
     */
    // Default needed for Term class
    public Expression(){}

    /**
     * Creates an expression containing a single term
     * @param term
     */
    public Expression(Term term){
        this.term = term;
    }

    /**
     * Create an expression with a negated term
     * @param right
     * @param op
     */
    public Expression(Expression right, Not op) {
        this.left = null; // Dirty, but Not is a unary operation.
        this.right = right;
        this.op = op;
    }

    /**
     * Creates an expression with left and right expressions, and an operator
     * @param left
     * @param right
     * @param op
     */
    public Expression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    /**
     * Returns the calculated value of the expression
     * @return The calculated value of the expression
     */
    public Term getValue() {
        if (op instanceof Not) {
            return op.calculate(null, right.getValue());
        } else {
            return op.calculate(left.getValue(), right.getValue());
        }
    }
}
