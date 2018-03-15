package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public abstract class Binary extends Expression {

    private final Expression leftExpression;
    private final Expression rightExpression;

    public Binary (int lineNumber, Expression leftExpression, Expression rightExpression) {
        super(lineNumber);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }
}
