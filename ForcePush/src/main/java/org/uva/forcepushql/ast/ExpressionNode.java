package org.uva.forcepushql.ast;


public abstract class ExpressionNode extends Node{ }

abstract class InfixExpressionNode extends ExpressionNode
{
    private Node Left;
    private Node Right;

    public void setLeft (ExpressionNode left)   { this.Left = left; }
    public void setRight (ExpressionNode right) { this.Right = right; }

    public Node getLeft ()    {  return this.Left;  }
    public Node getRight ()   {  return this.Right; }
}


class AdditionNode extends InfixExpressionNode { }

class SubtractionNode extends InfixExpressionNode { }

class MultiplicationNode extends InfixExpressionNode { }

class DivisionNode extends InfixExpressionNode { }

class NegateNode extends ExpressionNode
{
    private Node InnerNode;

    public void setInnerNode(Node innerNode) {
        this.InnerNode = innerNode;
    }

    public Node getInnerNode() {
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