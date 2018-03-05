package models.ast.elements.expressions.unary.arithmetics;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.unary.UnaryExpression;

public class Minus extends UnaryExpression{

    private Expression expression;

    public Minus(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
