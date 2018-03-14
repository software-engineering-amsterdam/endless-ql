package ast.model.expressions.unary;

import ast.model.expressions.Expression;

abstract public class UnaryExpression extends Expression {

    private Expression expression;

    protected UnaryExpression(Expression expression, MetaInformation metaInformation) {
        super(metaInformation);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}
