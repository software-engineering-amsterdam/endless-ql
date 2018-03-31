package ql.ast.expressions;

import ql.visitors.ExpressionVisitor;

public class GroupExpression extends Expression {
    private final Expression expression;

    public GroupExpression(int lineNumber, Expression expression) {
        super(lineNumber);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
