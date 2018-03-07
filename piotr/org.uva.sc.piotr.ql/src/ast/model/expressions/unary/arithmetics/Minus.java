package ast.model.expressions.unary.arithmetics;

import ast.model.expressions.Expression;
import ast.model.expressions.unary.UnaryExpression;

public class Minus extends UnaryExpression{
    public Minus(Expression expression, Integer startLine, Integer endLine) {
        super(expression, startLine, endLine);
    }
}
