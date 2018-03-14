package org.uva.forcepushql.ast;


public abstract class ExpressionNode { }

abstract class InfixExpressionNode extends ExpressionNode
{
    public ExpressionNode Left;
    public ExpressionNode Right;

    public void setLeft (ExpressionNode left){
        this.Left = left;
        System.out.println("InfixExpressionNode setLeft = " + this.Left);
    }
    public void setRight (ExpressionNode right){
        this.Right = right;
        System.out.println("InfixExpressionNode setRight = " + this.Right);
    }

    public ExpressionNode getLeft () {
        System.out.println("InfixExpressionNode getLeft = " + this.Left);
        return this.Left;
    }
    public ExpressionNode getRight () {
        System.out.println("InfixExpressionNode getRight = " + this.Right);
        return this.Right;
    }
}

class AdditionNode extends InfixExpressionNode { }

class SubtractionNode extends InfixExpressionNode { }

class MultiplicationNode extends InfixExpressionNode { }

class DivisionNode extends InfixExpressionNode { }

class NegateNode extends ExpressionNode
{
    public ExpressionNode InnerNode;

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

    public double getValue() {
        System.out.println("I have set and returning value of " + this.Value);
        return this.Value;
    }
}