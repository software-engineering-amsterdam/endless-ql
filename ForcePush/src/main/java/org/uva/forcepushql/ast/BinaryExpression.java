package org.uva.forcepushql.ast;

abstract class BinaryExpression
{
}


abstract class InfixExpressionNode extends BinaryExpression
{
    public BinaryExpression Left;
    public BinaryExpression Right;

    public void setLeft (BinaryExpression left){
        this.Left = left;
        System.out.println("this left is set " + this.Left);
    }

    public void setRight (BinaryExpression right){
        this.Right = right;
        System.out.println("this right is  set " + this.Right);
    }

    public BinaryExpression getLeft () {
        System.out.println("this left.get is " + this.Left);
        return this.Left;
    }

    public BinaryExpression getRight () {
        System.out.println("this Right.get is " + this.Right);
        return this.Right;
    }
}

class AdditionNode extends InfixExpressionNode
{
}

class SubtractionNode extends InfixExpressionNode
{
}

class MultiplicationNode extends InfixExpressionNode
{
}

class DivisionNode extends InfixExpressionNode
{
}

class NegateNode extends BinaryExpression
{
    public BinaryExpression InnerNode;

    public void setInnerNode(BinaryExpression innerNode) {
        this.InnerNode = innerNode;
    }

    public BinaryExpression getInnerNode() {
        return InnerNode;
    }
}

class FunctionNode extends BinaryExpression
{
    public BinaryExpression Argument;

    public void setArgument(BinaryExpression argument)
    {
        this.Argument = argument;
    }

    public BinaryExpression getArgument()
    {
        return Argument;
    }
}

class NumberNode extends BinaryExpression
{
    public double Value;

    public void setValue(double value)
    {
        this.Value = value;
    }

    public double getValue() {
        return this.Value;
    }
}