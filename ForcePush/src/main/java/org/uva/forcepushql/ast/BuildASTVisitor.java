package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;

public class BuildASTVisitor extends GrammarParserBaseVisitor<BinaryExpression>{


    @Override
    public BinaryExpression visitMathUnit(GrammarParser.MathUnitContext ctx) {
        return super.visitMathUnit(ctx);
    }

    @Override
    public BinaryExpression visitParensExpression(GrammarParser.ParensExpressionContext ctx) {
        return super.visitParensExpression(ctx);
    }

    @Override
    public BinaryExpression visitValueExpression(GrammarParser.ValueExpressionContext ctx) {
        return super.visitValueExpression(ctx);
    }

    @Override
    public BinaryExpression visitBinaryExpression(GrammarParser.BinaryExpressionContext context){

        InfixExpressionNode node;
        System.out.println("Context.op is " + context.op.getType() + " and GrammarParser.PLUS is " + GrammarParser.PLUS);
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
                node = new AdditionNode();
                /*try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
        }

        node.setLeft(visit(context.left));
        node.setRight(visit(context.right));
        node.getLeft();
        node.getRight();
        System.out.println("And finally node.Left is " + node.Left);

        return node;

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