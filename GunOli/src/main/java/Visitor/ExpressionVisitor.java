package Visitor;

import ParseObjects.Expressions.Expression;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;

public class ExpressionVisitor extends QLBaseVisitor<Expression>{


    public Expression visitBinaryExpression(QLParser.QuestionContext ctx){


        return null;
    }
    public Expression visitUnaryExpression(QLParser.QuestionContext ctx){


        return null;
    }

    public Expression visitConstants(){

        return null;
    }


}
