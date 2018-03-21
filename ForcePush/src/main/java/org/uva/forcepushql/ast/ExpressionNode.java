package org.uva.forcepushql.ast;


public abstract class ExpressionNode extends Node{ }

abstract class InfixExpressionNode extends ExpressionNode
{
    private Node left;
    private Node right;

    public void setLeft (Node left)   { this.left = left; }
    public void setRight (Node right) { this.right = right; }

    public Node getLeft ()    {  return this.left;  }
    public Node getRight ()   {  return this.right; }
}


class AdditionNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class SubtractionNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class MultiplicationNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class DivisionNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class AndNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class OrNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class LessNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class GreaterNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class EqualLessNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class EqualGreaterNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class NotEqualNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class IsEqualNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class NegateNode extends ExpressionNode
{
    private Node InnerNode;

    public void setInnerNode(Node innerNode) {
        this.InnerNode = innerNode;
    }

    public Node getInnerNode() {
        return InnerNode;
    }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
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

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
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

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class DecimalNode extends ExpressionNode
{
    private double value;

    public void setValue(double value)
    {
        this.value = value;
    }

    public double getValue() { return this.value; }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}
