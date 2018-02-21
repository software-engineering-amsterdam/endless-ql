package ql.ast.expressions.Binary;

import ql.ast.expressions.Expression;

public abstract class Binary extends Expression {

    private final Expression leftHandSide;
    private final Expression rightHandSide;

    public Binary (int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber);
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public Expression getLeftExpression() {
        return leftHandSide;
    }

    public Expression getRightExpression() {
        return rightHandSide;
    }
}
