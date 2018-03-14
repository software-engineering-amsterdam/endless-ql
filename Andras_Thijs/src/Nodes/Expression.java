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
