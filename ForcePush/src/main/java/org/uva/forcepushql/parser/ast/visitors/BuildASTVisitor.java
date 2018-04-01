package org.uva.forcepushql.parser.ast.visitors;

import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.antlr.GrammarParser.QuestionFormatContext;
import org.uva.forcepushql.parser.antlr.GrammarParserBaseVisitor;
import org.uva.forcepushql.parser.antlr.GrammarParserVisitor;
import org.uva.forcepushql.parser.ast.ValueType;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;


public class BuildASTVisitor extends GrammarParserBaseVisitor<Node> implements GrammarParserVisitor<Node>
{


    static InfixExpressionNode getInfixExpressionNode(int i)
    {
        InfixExpressionNode node;
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

        } else if (i == GrammarParser.PLUS)
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
        return node;
    }

    @Override
    public Node visitCompileUnit(GrammarParser.CompileUnitContext context)
    {
        return context.formStructure().accept(this);
    }

    @Override
    public Node visitFormStructure(GrammarParser.FormStructureContext context)
    {

        FormNode node = new FormNode();
        node.setName(context.variable().getText());
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.addOneQuestion(q.accept(this));
        }

        return node;
    }

    @Override
    public Node visitConditionalIf(GrammarParser.ConditionalIfContext context)
    {
        ConditionalNode node = new ConditionalNode();
        node.setCondition(context.ifCondition().accept(this));
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.addOneQuestion(q.accept(this));
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
        ConditionalNode node = new ConditionalNode();
        node.setCondition(context.ifCondition().accept(this));
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.addOneQuestion(q.accept(this));
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
        ConditionalNode node = new ConditionalNode();

        node.setCondition(null);
        for (GrammarParser.QuestionTypesContext q : context.questionTypes())
        {
            node.addOneQuestion(q.accept(this));
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
        QuestionNode node = new QuestionNode();
        LabelNode labelNode = new LabelNode();
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
        node.setType(ValueType.valueOfString(context.getText()));

        return node;
    }


    @Override
    public Node visitNumberExpression(GrammarParser.NumberExpressionContext context)
    {
        int i = context.value.getType();
        switch (i)
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
    public Node visitParenthesisExpression(GrammarParser.ParenthesisExpressionContext context)
    {
        return context.expression().accept(this);
    }

    @Override
    public Node visitLogicalExpression(GrammarParser.LogicalExpressionContext context)
    {
        InfixExpressionNode node;

        int i = context.log.getType();
        switch (i)
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

        node.setLeft((ExpressionNode) context.left.accept(this));
        node.setRight((ExpressionNode) context.right.accept(this));

        return node;
    }

    @Override
    public Node visitComparisonExpression(GrammarParser.ComparisonExpressionContext context)
    {

        InfixExpressionNode node;

        int i = context.comp.getType();
        node = getInfixExpressionNode(i);
        if (node == null) return null;

        node.setLeft((ExpressionNode) context.left.accept(this));
        node.setRight((ExpressionNode) context.right.accept(this));

        return node;
    }

    @Override
    public ExpressionNode visitInfixExpression(GrammarParser.InfixExpressionContext context)
    {

        InfixExpressionNode node;

        int i = context.op.getType();
        node = getInfixExpressionNode(i);


        node.setLeft((ExpressionNode) context.left.accept(this));
        node.setRight((ExpressionNode) context.right.accept(this));

        return node;

    }

    @Override
    public Node visitUnaryExpression(GrammarParser.UnaryExpressionContext context)
    {
        NotNode notNode = new NotNode();
        notNode.setInnerNode((ExpressionNode) context.expression().accept(this));
        notNode.getInnerNode();
        return notNode;
    }


}

