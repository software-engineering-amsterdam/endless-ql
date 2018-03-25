package org.uva.forcepushql.ast;

public abstract class InfixExpressionNode extends ExpressionNode
{
    private ExpressionNode left;
    private ExpressionNode right;

    public void setLeft (ExpressionNode left)   { this.left = left; }
    public void setRight (ExpressionNode right) { this.right = right; }

    public ExpressionNode getLeft ()    {  return this.left;  }
    public ExpressionNode getRight ()   {  return this.right; }
}


class AdditionNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Double accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class SubtractionNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Double accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class MultiplicationNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Double accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class DivisionNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Double accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class AndNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class OrNode extends InfixExpressionNode {
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class LessNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class GreaterNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class EqualLessNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class EqualGreaterNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class NotEqualNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class IsEqualNode extends InfixExpressionNode{
    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class NegateNode extends ExpressionNode
{
    private ExpressionNode InnerNode;

    public void setInnerNode(ExpressionNode innerNode) {
        this.InnerNode = innerNode;
    }

    public ExpressionNode getInnerNode() {
        return InnerNode;
    }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}

class VariableNode extends ExpressionNode{
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

    @Override
    public Boolean accept(ASTExpressionVisitor visitor) {
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

    @Override
    public Double accept(ASTExpressionVisitor visitor) {
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

    @Override
    public Double accept(ASTExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}
