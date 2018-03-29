package ql.model.expression.unary;

import ql.model.expression.Expression;

public abstract class ExpressionUnary extends Expression {
    private final Expression operand;

    ExpressionUnary(Expression operand) {
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }
}