package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;

public class BuildASTVisitor extends GrammarParserBaseVisitor{

    public Expression VisitBinaryExpression(GrammarParser.ExpressionContext context){

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
        }

        node.left = visit(context.left);
        node.right = visit(context.right);

        return node;

    }
}
