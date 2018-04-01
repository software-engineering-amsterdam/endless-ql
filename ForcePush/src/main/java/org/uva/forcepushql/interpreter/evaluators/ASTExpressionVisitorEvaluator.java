package org.uva.forcepushql.interpreter.evaluators;

import org.uva.forcepushql.parser.ast.elements.NumberNode;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;
import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;

public class ASTExpressionVisitorEvaluator implements ASTExpressionVisitor
{

    public double visit(AdditionNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left + right;
    }

    public double visit(SubtractionNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left - right;
    }

    @Override
    public double visit(NumberNode node)
    {
        return node.getValue();
    }

    @Override
    public boolean visit(VariableNode node)
    {
        return Boolean.valueOf(node.name);
    }

    @Override
    public double visit(DecimalNode node)
    {
        return node.getValue();
    }

    public double visit(MultiplicationNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left * right;
    }

    public double visit(DivisionNode node)
    {
        double divisor = node.getRight().accept(this);
        if (divisor != 0.0)
        {
            double left = node.getLeft().accept(this);
            return left / divisor;
        } else
        {
            throw new ArithmeticException("Division by zero.");
        }
    }

    @Override
    public boolean visit(AndNode node)
    {
        boolean left = node.getLeft().accept(this);
        boolean right = node.getRight().accept(this);
        return left && right;
    }

    @Override
    public boolean visit(OrNode node)
    {
        boolean left = node.getLeft().accept(this);
        boolean right = node.getRight().accept(this);
        return left || right;
    }

    @Override
    public boolean visit(LessNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left < right;
    }

    @Override
    public boolean visit(GreaterNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left > right;
    }

    @Override
    public boolean visit(EqualLessNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left <= right;
    }

    @Override
    public boolean visit(EqualGreaterNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left >= right;
    }

    @Override
    public boolean visit(NotEqualNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left != right;
    }

    @Override
    public boolean visit(IsEqualNode node)
    {
        double left = node.getLeft().accept(this);
        double right = node.getRight().accept(this);
        return left == right;
    }


    public boolean visit(NotNode node)
    {
        boolean value = node.getInnerNode().accept(this);
        return !value;
    }


}
