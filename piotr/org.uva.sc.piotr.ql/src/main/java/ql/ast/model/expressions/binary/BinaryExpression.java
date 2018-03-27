package ql.ast.model.expressions.binary;

import ql.ast.model.expressions.Expression;

public abstract class BinaryExpression extends Expression {

    private Expression leftSide;
    private Expression rightSide;

    protected BinaryExpression(Expression leftSide, Expression rightSide, MetaInformation metaInformation) {
        super(metaInformation);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    protected BinaryExpression(Expression leftSide, Expression rightSide) {
        super();
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Expression getLeftSide() {
        return leftSide;
    }

    public Expression getRightSide() {
        return rightSide;
    }

}
