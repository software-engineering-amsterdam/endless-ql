package ast.model.expressions.unary;

import ast.model.expressions.Expression;

abstract public class UnaryExpression extends Expression {

    public UnaryExpression(Integer startLine, Integer endLine) {
        super(startLine, endLine);
    }
}
