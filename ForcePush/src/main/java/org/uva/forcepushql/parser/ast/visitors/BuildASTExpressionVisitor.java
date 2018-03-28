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

        InfixExpressionNode node;

        int i = context.comp.getType();
        if (i == GrammarParser.LESS)
        {
            node = new LessNode();

        } else if (i == GrammarParser.GREATER)
        {
            node = new GreaterNode();

        } else if (i == GrammarParser.EQUALLESS)
        {
            node = new EqualLessNode();

        } else if (i == GrammarParser.EQUALGREATER)
        {
            node = new EqualGreaterNode();

        } else if (i == GrammarParser.NOTEQUAL)
        {
            node = new NotEqualNode();

        } else if (i == GrammarParser.ISEQUAL)
        {
            node = new IsEqualNode();

        } else
        {
            try
            {
                throw new Exception("Invalid Node type");
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
    public ExpressionNode visitInfixExpression(GrammarParser.InfixExpressionContext context)
    {

        InfixExpressionNode node;

        int i = context.op.getType();
        if (i == GrammarParser.PLUS)
        {
            node = new AdditionNode();

        } else if (i == GrammarParser.MINUS)
        {
            node = new SubtractionNode();

        } else if (i == GrammarParser.MULTIPLY)
        {
            node = new MultiplicationNode();

        } else if (i == GrammarParser.DIVIDE)
        {
            node = new DivisionNode();

        } else
        {
            try
            {
                throw new Exception("Invalid Node type");
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
    public ExpressionNode visitUnaryExpression(GrammarParser.UnaryExpressionContext context)
    {
        NegateNode negateNode = new NegateNode();
        negateNode.setInnerNode(context.expression().accept(this));
        negateNode.getInnerNode();
        return negateNode;
    }
}
