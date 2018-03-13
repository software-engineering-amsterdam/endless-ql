package QL.QLVisitor;

import QL.ParseObjectsQL.Expressions.BinaryExpressions.*;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.*;
import QL.ParseObjectsQL.Expressions.ConstantExpression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NegationExpression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NotExpression;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

public class ExpressionVisitor extends QLBaseVisitor<Expression> {
    private ExpressionTable expressionTable;

    public ExpressionVisitor(ExpressionTable exprTable){
        super();
        this.expressionTable = exprTable;
    }

    @Override
    public Expression visitBinaryExpr(QLParser.BinaryExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);
        String operator = ctx.operator().getText();

        if (operator.charAt(0) == '+') {
            return new AdditionExpression(left, right);
        }
        else if (operator.charAt(0) == '-') {
            return new SubtractExpression(left, right);
        }
        else if (operator.charAt(0) == '*') {
            return new MultiplicationExpression(left, right);
        }
        else if (operator.charAt(0) == '/') {
            return new DivisionExpression(left, right);
        }

        else if (operator.charAt(0) == '>') {
            if (operator.charAt(1) == '=') {
                return new GreaterOrEqualExpression(left, right);
            }
            else {
                return new GreaterThanExpression(left, right);
            }
        }

        else if (operator.charAt(0) == '<') {
            if (operator.charAt(1) == '=') {
                return new LessOrEqualExpression(left, right);
            }
            else {
                return new LessThanExpression(left, right);
            }
        }

        else if (operator.charAt(0) == '=') {
            return new EqualExpression(left, right);
        }
        else if (operator.charAt(0) == '!') {
            return new NotEqualExpression(left, right);
        }
        else if (operator.charAt(0) == '&') {
            return new AndExpression(left, right);
        }
        else if (operator.charAt(0) == '|') {
            return new OrExpression(left, right);
        }

        return null;//Todo: Make this better, need to return an error or empty object?
    }

    @Override
    public Expression visitUnaryExpr(QLParser.UnaryExprContext ctx) {
        Expression expr = visit(ctx.expression());
        String operator = ctx.operator().getText();

        if (operator.charAt(0) == '-') {
            return new NegationExpression(expr);
        }
        else if (operator.charAt(0) == '!') {
            return new NotExpression(expr);
        }

        return null;//Todo: Make this better, need to return an error or empty object?
    }

    @Override
    public Expression visitNestedExpr(QLParser.NestedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        return new StringConstant(ctx.getText());
    }

    @Override
    public Expression visitDateConstant(QLParser.DateConstantContext ctx) {
        return new DateConstant(ctx.getText());
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        return new IntegerConstant(Integer.parseInt(ctx.getText()));
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        return new DecimalConstant(Double.parseDouble(ctx.getText()));
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        return new MoneyConstant(Double.parseDouble(ctx.getText()));
    }

    //Todo: Boolean Constant?
    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx){
        return new ConstantExpression(ctx.IDENTIFIER().getText(), expressionTable);
    }
}