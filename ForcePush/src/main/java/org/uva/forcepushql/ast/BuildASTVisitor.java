package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;

public class BuildASTVisitor extends GrammarParserBaseVisitor{


    public Expression visitExpression (GrammarParser.ExpressionContext context){

        if(context instanceof GrammarParser.BinaryExpressionContext)
            return visitBinaryExpression((GrammarParser.BinaryExpressionContext)context);

        else if(context instanceof GrammarParser.ParensExpressionContext)
            return visitParensExpression((GrammarParser.ParensExpressionContext)context);

        else
            return visitValueExpression((GrammarParser.ValueExpressionContext)context);

    }

    public Expression visitParensExpression (GrammarParser.ParensExpressionContext context){
        return visitExpression(context.expression());
    }

    public ValueExpression visitValueExpression (GrammarParser.ValueExpressionContext context){
        return new ValueExpression();
    }


    public Variable visitVariable (GrammarParser.VariableContext context){
        return new Variable();
    }


    public BinaryExpression visitBinaryExpression(GrammarParser.BinaryExpressionContext context){

        BinaryExpression node = null;

        switch(context.arithmetic().getRuleIndex()){
            case GrammarParser.PLUS:
                node = new PlusExpression();
                break;

            case GrammarParser.MINUS:
                node = new MinusExpression();
                break;

            case GrammarParser.MULTIPLY:
                node = new MultiplyExpression();
                break;

            case GrammarParser.DIVIDE:
                node = new DivideExpression();
                break;

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

            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        node.setLeft(visitExpression(context.left));
        node.setRight(visitExpression(context.right));

        return node;

    }
}
