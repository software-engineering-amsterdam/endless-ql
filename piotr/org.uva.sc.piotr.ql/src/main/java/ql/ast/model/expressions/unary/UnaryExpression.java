package ql.ast.model.expressions.unary;

import ql.ast.model.expressions.Expression;

abstract public class UnaryExpression extends Expression {

    private final Expression expression;

    protected UnaryExpression(Expression expression, MetaInformation metaInformation) {
        super(metaInformation);
        this.expression = expression;
    }

    protected UnaryExpression(Expression expression) {
        super();
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}
