package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParser.QuestionFormatContext;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;
import org.uva.forcepushql.antlr.GrammarParserVisitor;

import java.util.List;

public class BuildASTVisitor extends GrammarParserBaseVisitor<Node> implements GrammarParserVisitor<Node>{


    @Override
    public Node visitCompileUnit(GrammarParser.CompileUnitContext context) {
        return visit(context.formStructure());
    }

    @Override
    public Node visitFormStructure(GrammarParser.FormStructureContext context) {

        FormNode node = new FormNode();
        node.setName(context.variable().getText());
        for (GrammarParser.QuestionTypesContext q: context.questionTypes()) {
            node.setOneQuestion(visit(q));
        }

        return node;
    }

    public Node visitConditionalIf(GrammarParser.ConditionalIfContext context) {
        ConditionalIfNode node = new ConditionalIfNode();

        node.setCondition(visit(context.variable()));//IT IS NEEDED TO CHANGE THIS!!!
        for (GrammarParser.QuestionTypesContext q: context.questionTypes()) {
            node.setOneQuestion(visit(q));
        }
        return node;
    }

    @Override
    public Node visitMathUnit(GrammarParser.MathUnitContext context) {
        return visit(context.expression());
    }

    @Override
    public Node visitQuestionFormat(QuestionFormatContext context) {
        QuestionNode node = new QuestionNode();
        LabelNode labelNode = new LabelNode();
        labelNode.setLabel(context.LABEL().getText());
        node.setLeft(labelNode);
        node.setCenter(visit(context.variable()));
        node.setRight(visit(context.type()));

        return node;
    }

    @Override
    public Node visitVariable(GrammarParser.VariableContext context) {
        NameNode node = new NameNode();
        node.setName(context.getText());

        return node;
    }

    @Override
    public Node visitType(GrammarParser.TypeContext context) {
        TypeNode node = new TypeNode();
        node.setType(context.getText());

        return node;
    }

    @Override
    public ExpressionNode visitNumberExpression(GrammarParser.NumberExpressionContext context) {
        System.out.println("I visited this leaf with value = " + context.value.getText());
        NumberNode number = new NumberNode();
        number.setValue(Double.valueOf(context.value.getText()));
        number.getValue();
        return number;
    }

    @Override
    public Node visitParenthesisExpression(GrammarParser.ParenthesisExpressionContext context) {
        return visit(context.expression());
    }

    @Override
    public ExpressionNode visitInfixExpression(GrammarParser.InfixExpressionContext context){

        InfixExpressionNode node;

        switch(context.op.getType()){
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
                try {
                    throw new Exception("Invalid Node type");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
        }


        node.setLeft((ExpressionNode) visit(context.left));
        System.out.println("End of node.Left \n");


        node.setRight((ExpressionNode) visit(context.right));
        System.out.println("End of node.Right \n");

        System.out.println("node.Left is " + node.getLeft());
        System.out.println("node.Right is " + node.getRight());
        System.out.println(node);

        return node;

    }

    @Override
    public Node visitUnaryExpression(GrammarParser.UnaryExpressionContext context) {
        switch (context.op.getType()){
            case GrammarParser.PLUS:
                return visit(context.expression());
            case GrammarParser.MINUS:
            {
                NegateNode negateNode = new NegateNode();
                negateNode.setInnerNode(visit(context.expression()));
                negateNode.getInnerNode();
                return negateNode;
            }
            default:
                return null;
        }
    }


}



/* To be used later


            case GrammarParser.AND:
                node = new AndExpression();
                break;

            case GrammarParser.OR:
                node = new OrExpression();
                break;

            case GrammarParser.LESS:
                node = new LessExpression();
                break;

            case GrammarParser.GREATER:
                node = new GreaterExpression();
                break;

            case GrammarParser.EQUALLESS:
                node = new EqualLessExpression();
                break;

            case GrammarParser.EQUALGREATER:
                node = new EqualGreaterExpression();
                break;

            case GrammarParser.NOTEQUAL:
                node = new NotEqualExpression();
                break;

            case GrammarParser.ISEQUAL:
                node = new IsEqualExpression();
                break;
*/