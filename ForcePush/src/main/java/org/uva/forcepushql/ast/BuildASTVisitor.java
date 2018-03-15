package org.uva.forcepushql.ast;

import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.antlr.GrammarParserBaseVisitor;
import org.uva.forcepushql.antlr.GrammarParserVisitor;

public class BuildASTVisitor extends GrammarParserBaseVisitor<ExpressionNode> implements GrammarParserVisitor<ExpressionNode>{


    @Override
    public ExpressionNode visitMathUnit(GrammarParser.MathUnitContext context) {
        return visit(context.expression());
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
    public ExpressionNode visitParenthesisExpression(GrammarParser.ParenthesisExpressionContext context) {
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


        node.Left = visit(context.left);
        System.out.println("End of node.Left \n");


        node.Right = visit(context.right);
        System.out.println("End of node.Right \n");

        System.out.println("node.Left is " + node.Left);
        System.out.println("node.Right is " + node.Right);
        System.out.println(node);

        return node;

    }

    @Override
    public ExpressionNode visitUnaryExpression(GrammarParser.UnaryExpressionContext context) {
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