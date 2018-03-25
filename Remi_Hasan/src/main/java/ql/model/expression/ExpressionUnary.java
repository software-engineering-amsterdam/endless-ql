package ql.model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionUnary extends Expression {
    private final Expression subExpresison;

    public ExpressionUnary(Token start, Expression expression) {
        super(start);
        this.subExpresison = expression;
    }

    public Expression getSubExpresison() {
        return subExpresison;
    }
}