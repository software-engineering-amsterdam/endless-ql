package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class GreaterEqual extends BinaryOperator {

    public GreaterEqual(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return ">=";
    }
}
