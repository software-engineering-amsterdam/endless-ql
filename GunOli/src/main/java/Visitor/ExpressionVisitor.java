package Visitor;

import ParseObjects.Expressions.Expression;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;
import antlrGen.QLLexer;

public class ExpressionVisitor extends QLBaseVisitor<Expression>{

    @Override
    public Expression visitBinaryExpr(QLParser.BinaryExprContext ctx){
        Expression left = visit(ctx.left);
        Expression right= visit(ctx.right);

        return null;
    }

    @Override
    public Expression visitUnaryExpr(QLParser.UnaryExprContext ctx){


        return null;
    }

    @Override
    public Expression visitConstantExpr(QLParser.ConstantExprContext ctx){

        return null;
    }


}
