package ql.model.expression.unary;

import org.antlr.v4.runtime.Token;
import ql.model.expression.Expression;

public abstract class ExpressionUnary extends Expression {
    private final Expression operand;

    public ExpressionUnary(Token start, Expression operand) {
        super(start);
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }
}