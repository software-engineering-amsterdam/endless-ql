package org.uva.forcepushql.ast;


public abstract class ExpressionNode { }

abstract class InfixExpressionNode extends ExpressionNode
{
    public ExpressionNode Left;
    public ExpressionNode Right;

    public void setLeft (ExpressionNode left)   { this.Left = left; }
    public void setRight (ExpressionNode right) { this.Right = right; }

    public ExpressionNode getLeft ()    {  return this.Left;  }
    public ExpressionNode getRight ()   {  return this.Right; }
}


class AdditionNode extends InfixExpressionNode { }

class SubtractionNode extends InfixExpressionNode { }

class MultiplicationNode extends InfixExpressionNode { }

class DivisionNode extends InfixExpressionNode { }

class NegateNode extends ExpressionNode
{
    private ExpressionNode InnerNode;

    public void setInnerNode(ExpressionNode innerNode) {
        this.InnerNode = innerNode;
    }

    public ExpressionNode getInnerNode() {
        return InnerNode;
    }
}

class NumberNode extends ExpressionNode
{
    private double Value;

    public void setValue(double value)
    {
        this.Value = value;
    }

    public double getValue() { return this.Value; }
}