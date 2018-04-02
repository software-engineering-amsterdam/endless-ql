package org.uva.forcepushql.parser.ast.visitors;

import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.antlr.GrammarParserBaseVisitor;
import org.uva.forcepushql.parser.antlr.GrammarParserVisitor;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;


public class BuildASTExpressionVisitor extends GrammarParserBaseVisitor<ExpressionNode> implements GrammarParserVisitor<ExpressionNode> {

    @Override
    public ExpressionNode visitMathUnit(GrammarParser.MathUnitContext context)
    {
        return context.expression().accept(this);
    }

    @Override
    public ExpressionNode visitNumberExpression(GrammarParser.NumberExpressionContext context)
    {
        int i = context.value.getType();
        if (i == GrammarParser.NUM)
        {
            NumberNode node = new NumberNode();
            node.setValue(Integer.valueOf(context.getText()));
            return node;
        } else if (i == GrammarParser.VAR)
        {
            VariableNode node = new VariableNode();
            node.setName(context.getText());
            return node;
        } else if (i == GrammarParser.DEC)
        {
            DecimalNode node = new DecimalNode();
            node.setValue(Double.valueOf(context.getText()));
            return node;
        }
        return null;
    }

    @Override
    public ExpressionNode visitParenthesisExpression(GrammarParser.ParenthesisExpressionContext context)
    {
        return context.expression().accept(this);
    }

    @Override
    public ExpressionNode visitLogicalExpression(GrammarParser.LogicalExpressionContext context)
    {
        InfixExpressionNode node;

        int i = context.log.getType();
        if (i == GrammarParser.AND)
        {
            node = new AndNode();

        } else if (i == GrammarParser.OR)
        {
            node = new OrNode();

        } else
        {
            try
            {
                throw new Exception("Invalid Node Type");
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        node.setLeft(context.left.accept(this));
        node.setRight(context.right.accept(this));

        return node;
    }

    @Override
    public ExpressionNode visitComparisonExpression(GrammarParser.ComparisonExpressionContext context)
    {

        int i = context.comp.getType();
        InfixExpressionNode node = BuildASTVisitor.getInfixExpressionNode(i);
        if (node == null) return null;

        node.setLeft(context.left.accept(this));
        node.setRight(context.right.accept(this));

        return node;
    }

    @Override
    public ExpressionNode visitInfixExpression(GrammarParser.InfixExpressionContext context)
    {

        InfixExpressionNode node;

        int i = context.op.getType();
        node = BuildASTVisitor.getInfixExpressionNode(i);

        node.setLeft(context.left.accept(this));
        node.setRight(context.right.accept(this));

        return node;

    }

    @Override
    public ExpressionNode visitUnaryExpression(GrammarParser.UnaryExpressionContext context)
    {
        NotNode notNode = new NotNode();
        notNode.setInnerNode(context.expression().accept(this));
        notNode.getInnerNode();
        return notNode;
    }
}
