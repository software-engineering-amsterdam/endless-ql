package Nodes;

import Nodes.Operator.*;
import Nodes.Term.Term;

public class Expression {
    private Expression left;
    private Expression right;
    private Operator op;

    public Expression(){}
    public Expression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public Term getValue() {
        return op.calculate(left.getValue(), right.getValue());
    }
}
