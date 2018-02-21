package main.org.uva.ql.ast.expression;

import main.org.uva.ql.visitor.ExpressionVisitor;

public class ParameterGroup extends Expression {

    private final Expression expression;

    public ParameterGroup(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return this.expression.toString();
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
