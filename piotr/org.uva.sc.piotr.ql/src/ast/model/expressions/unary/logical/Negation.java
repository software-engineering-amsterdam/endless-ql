package ast.model.expressions.unary.logical;

import ast.model.expressions.Expression;
import ast.model.expressions.unary.UnaryExpression;

public class Negation extends UnaryExpression{
    public Negation(Expression expression, Integer startLine, Integer endLine) {
        super(expression, startLine, endLine);
    }
}
