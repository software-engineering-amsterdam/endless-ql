package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;

public class BuildASTVisitor extends GrammarParserBaseVisitor{


    public Expression visitExpression (GrammarParser.ParensExpressionContext context){
        return visitExpression(context.expression());
    }


    public Expression visitExpression (GrammarParser.ExpressionContext context){
        
    }

    public Variable visitVariable (GrammarParser.VariableContext context){
        return new Variable();
    }


    public BinaryExpression visitBinaryExpression(GrammarParser.BinaryExpressionContext context){

        BinaryExpression node;

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

            default: node = new PlusExpression();
        }

        node.left = visitExpression(context.left);
        node.right = visitExpression(context.right);

        return node;

    }
}
