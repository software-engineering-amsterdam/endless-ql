package models.ast.elements.expressions.binary;

import models.ast.elements.expressions.Expression;

public class BinaryExpression extends Expression {

    private Expression leftSide;
    private Expression rightSide;

    public BinaryExpression(Expression leftSide, Expression rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Expression getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(Expression leftSide) {
        this.leftSide = leftSide;
    }

    public Expression getRightSide() {
        return rightSide;
    }

    public void setRightSide(Expression rightSide) {
        this.rightSide = rightSide;
    }
}
