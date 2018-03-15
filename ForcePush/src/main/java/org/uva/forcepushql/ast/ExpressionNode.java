package org.uva.forcepushql.ast;


public abstract class ExpressionNode extends Node{ }

abstract class InfixExpressionNode extends ExpressionNode
{
    private Node left;
    private Node Right;

    public void setLeft (ExpressionNode left)   { this.left = left; }
    public void setRight (ExpressionNode right) { this.Right = right; }

    public Node getLeft ()    {  return this.left;  }
    public Node getRight ()   {  return this.Right; }
}


class AdditionNode extends InfixExpressionNode { }

class SubtractionNode extends InfixExpressionNode { }

class MultiplicationNode extends InfixExpressionNode { }

class DivisionNode extends InfixExpressionNode { }

class AndNode extends InfixExpressionNode { }

class OrNode extends InfixExpressionNode { }

class LessNode extends InfixExpressionNode{ }

class GreaterNode extends InfixExpressionNode{ }

class EqualLessNode extends InfixExpressionNode{ }

class EqualGreaterNode extends InfixExpressionNode{ }

class NotEqualNode extends InfixExpressionNode{ }

class IsEqualNode extends InfixExpressionNode{ }

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

class Variable extends ExpressionNode{
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class NumberNode extends ExpressionNode
{
    private int value;

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue() { return this.value; }
}

class DecimalNode extends ExpressionNode
{
    private double value;

    public void setValue(double value)
    {
        this.value = value;
    }

    public double getValue() { return this.value; }
}
