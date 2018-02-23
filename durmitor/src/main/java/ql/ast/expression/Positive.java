package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class Positive extends UnaryOperation {

    public Positive(Expression operand) {
        super(operand);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
