package org.uva.forcepushql.parser.ast.elements;

public abstract class InfixExpressionNode extends ExpressionNode
{
    private ExpressionNode left;
    private ExpressionNode right;

    public InfixExpressionNode(boolean isBooleanExpression) {
        super(isBooleanExpression);
    }

    public void setLeft(ExpressionNode left)
    {
        this.left = left;
    }

    public void setRight(ExpressionNode right)
    {
        this.right = right;
    }

    public ExpressionNode getLeft()
    {
        return this.left;
    }

    public ExpressionNode getRight()
    {
        return this.right;
    }

}


