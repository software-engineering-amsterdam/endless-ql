package models.ast.elements.expressions.unary.negation;

import models.ast.elements.expressions.Expression;

public class Negation {

    private Expression expression;

    public Negation(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
