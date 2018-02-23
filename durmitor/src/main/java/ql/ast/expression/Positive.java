package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class Positive extends UnaryOperator {

    public Positive(Expression expr) { 
        super.operand = expr;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
