package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class Divide extends BinaryOperation {

    public Divide(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "/";
    }
}
