package models.ast.elements.expressions.unary.logical;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.unary.UnaryExpression;

public class Negation extends UnaryExpression{

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
