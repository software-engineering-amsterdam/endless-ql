package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class And extends BinaryOperator {

    public And(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public String getOperator() {
        return "&&";
    }
}
