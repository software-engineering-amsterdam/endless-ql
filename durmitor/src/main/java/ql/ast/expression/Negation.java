package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class Negation extends UnaryOperator {

    public Negation(Expression expr) { 
        super.operand = expr;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "!";
    }
}
