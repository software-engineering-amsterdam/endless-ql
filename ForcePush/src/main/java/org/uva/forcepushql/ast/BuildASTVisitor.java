package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParser.QuestionFormatContext;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;
import org.uva.forcepushql.antlr.GrammarParserVisitor;


public class BuildASTVisitor extends GrammarParserBaseVisitor<Node> implements GrammarParserVisitor<Node>
{


    @Override
    public Node visitCompileUnit(GrammarParser.CompileUnitContext context)
    {
        return context.accept(this);
    }

    @Override
    public Node visitFormStructure(GrammarParser.FormStructureContext context)
    {

        FormNode node = new FormNode();
        node.setName(context.variable().getText());
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.setOneQuestion(q.accept(this));
        }

        return node;
    }

    @Override
    public Node visitConditionalIf(GrammarParser.ConditionalIfContext context)
    {
        ConditionalIfNode node = new ConditionalIfNode();

        node.setCondition(context.ifCondition().accept(this));
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.setOneQuestion(q.accept(this));
        }

        for (GrammarParser.NextConditionContext c : context.nextCondition())
        {
            node.setAfter(c.accept(this));
        }


        return node;
    }

    @Override
    public Node visitConditionalIfElse(GrammarParser.ConditionalIfElseContext context)
    {
        ConditionalIfElseNode node = new ConditionalIfElseNode();
        node.setCondition(context.ifCondition().accept(this));
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.setOneQuestion(q.accept(this));
        }

        for (GrammarParser.NextConditionContext c : context.nextCondition())
        {
            node.setAfter(c.accept(this));
        }

        return node;
    }

    @Override
    public Node visitConditionalElse(GrammarParser.ConditionalElseContext context)
    {
        ConditionalElseNode node = new ConditionalElseNode();

        node.setCondition(null);
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.setOneQuestion(q.accept(this));
        }
        return node;
    }

    @Override
    public Node visitQuestionAssignValue(GrammarParser.QuestionAssignValueContext context)
    {
        QuestionAssignValueNode node = new QuestionAssignValueNode();
        node.setPrevious(context.questionFormat().accept(this));
        node.setExpression(context.expression().accept(this));

        return node;
    }

    @Override
    public Node visitMathUnit(GrammarParser.MathUnitContext context)
    {
        return context.expression().accept(this);
    }

    @Override
    public Node visitQuestionFormat(QuestionFormatContext context)
    {
        QuestionNode node      = new QuestionNode();
        LabelNode    labelNode = new LabelNode();
        labelNode.setLabel(context.LABEL().getText());
        node.setLeft(labelNode);
        node.setCenter(context.variable().accept(this));
        node.setRight(context.type().accept(this));

        return node;
    }

    @Override
    public Node visitVariable(GrammarParser.VariableContext context)
    {
        NameNode node = new NameNode();
        node.setName(context.getText());

        return node;
    }

    @Override
    public Node visitType(GrammarParser.TypeContext context)
    {
        TypeNode node = new TypeNode();
        node.setType(context.getText());

        return node;
    }

    @Override
    public Node visitNumberExpression(GrammarParser.NumberExpressionContext context)
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
                Variable node = new Variable();
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
    public Node visitParenthesisExpression(GrammarParser.ParenthesisExpressionContext context)
    {
        return context.expression().accept(this);
    }

    @Override
    public Node visitLogicalExpression(GrammarParser.LogicalExpressionContext context)
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
    public Node visitComparisonExpression(GrammarParser.ComparisonExpressionContext context)
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
    public Node visitUnaryExpression(GrammarParser.UnaryExpressionContext context)
    {
        switch (context.op.getType())
        {
            case GrammarParser.PLUS:
                return context.expression().accept(this);
            case GrammarParser.MINUS:
            {
                NegateNode negateNode = new NegateNode();
                negateNode.setInnerNode(context.expression().accept(this));
                negateNode.getInnerNode();
                return negateNode;
            }
            default:
                return null;
        }
    }


}

