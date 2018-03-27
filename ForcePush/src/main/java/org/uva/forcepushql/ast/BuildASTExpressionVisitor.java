package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;
import org.uva.forcepushql.antlr.GrammarParserVisitor;


public class BuildASTExpressionVisitor extends GrammarParserBaseVisitor<ExpressionNode> implements GrammarParserVisitor<ExpressionNode> {

    @Override
    public ExpressionNode visitMathUnit(GrammarParser.MathUnitContext context)
    {
        return context.expression().accept(this);
    }

    @Override
    public ExpressionNode visitNumberExpression(GrammarParser.NumberExpressionContext context)
    {
        switch (context.value.getType())
        {
            case GrammarParser.NUM:
            {
                NumberNode node = new NumberNode();
                node.setValue(Integer.valueOf(context.getText()));
                return node;
            }

            case GrammarParser.VAR:
            {
                VariableNode node = new VariableNode();
                node.setName(context.getText());
                return node;
            }

            case GrammarParser.DEC:
            {
                DecimalNode node = new DecimalNode();
                node.setValue(Double.valueOf(context.getText()));
                return node;
            }

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

        switch (context.log.getType())
        {
            case GrammarParser.AND:
                node = new AndNode();
                break;

            case GrammarParser.OR:
                node = new OrNode();
                break;

            default:
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

        switch (context.comp.getType())
        {
            case GrammarParser.LESS:
                node = new LessNode();
                break;

            case GrammarParser.GREATER:
                node = new GreaterNode();
                break;

            case GrammarParser.EQUALLESS:
                node = new EqualLessNode();
                break;

            case GrammarParser.EQUALGREATER:
                node = new EqualGreaterNode();
                break;

            case GrammarParser.NOTEQUAL:
                node = new NotEqualNode();
                break;

            case GrammarParser.ISEQUAL:
                node = new IsEqualNode();
                break;

            default:
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

        switch (context.op.getType())
        {
            case GrammarParser.PLUS:
                node = new AdditionNode();
                break;

            case GrammarParser.MINUS:
                node = new SubtractionNode();
                break;

            case GrammarParser.MULTIPLY:
                node = new MultiplicationNode();
                break;
            case GrammarParser.DIVIDE:
                node = new DivisionNode();
                break;

            default:
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
