package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class GreaterEqual extends BinaryOperation {

    public GreaterEqual(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return ">=";
    }
}
