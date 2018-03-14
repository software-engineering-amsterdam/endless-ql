package Nodes;

import Nodes.Operator.Operator;
import Nodes.Operator.Not;
import Nodes.Term.Boolean;
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
     * Creates an expression with a single expression inside
     * @param expression
     */
    public Expression(Expression expression){
        this.left = expression;
    }

    /**
     * Creates an expression containing a single term
     * @param term contains a instance of the abstract Term class
     */
    public Expression(Term term){
        this.term = term;
    }

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
     * Returns the calculated value of the expression
     * @return The calculated value of the expression
     */
    public Term getTerm() {
        if (op instanceof Not) {
            return op.calculate(null, right.getTerm());
        } else {
            return op.calculate(left.getTerm(), right.getTerm());
        }
    }

    public boolean getBoolean() {
        Boolean term = (Boolean) this.getTerm();
        return term.getBoolean();
    }
}
