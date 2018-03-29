package ql.model.expression.unary;

import ql.model.expression.Expression;

public abstract class UnaryExpression extends Expression {
    private final Expression operand;

    UnaryExpression(Expression operand) {
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }
}