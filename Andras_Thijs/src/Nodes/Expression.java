package Nodes;

import Nodes.Operator.*;
import Nodes.Term.Term;

public class Expression {
    private Expression left;
    private Expression right;
    private Operator op;

    // Default needed for Term class
    public Expression(){}

    public Expression(Expression right, Not op) {
        this.left = null; // Dirty, but Not is a unary operation.
        this.right = right;
        this.op = op;
    }

    public Expression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public Term getValue() {
        if (op instanceof Not) {
            return op.calculate(null, right.getValue());
        } else {
            return op.calculate(left.getValue(), right.getValue());
        }
    }
}
