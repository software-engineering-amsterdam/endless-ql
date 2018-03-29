package ql.visitor;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnary;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class VisitorExpression extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
        Expression value = visit(ctx.expr);
        ExpressionUnaryNot expressionUnaryNot = new ExpressionUnaryNot(value);
        expressionUnaryNot.setToken(ctx.getStart());
        return expressionUnaryNot;
    }

    @Override
    public Expression visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        ExpressionBinary expressionBinary;
        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.PLUS:
                expressionBinary = new ExpressionArithmeticSum(left, right);
                break;
            case QLLexer.MINUS:
                expressionBinary = new ExpressionArithmeticSubtract(left, right);
                break;
            case QLLexer.MUL:
                expressionBinary = new ExpressionArithmeticMultiply(left, right);
                break;
            case QLLexer.DIV:
                expressionBinary = new ExpressionArithmeticDivide(left, right);
                break;
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
        expressionBinary.setToken(ctx.getStart());
        return expressionBinary;
    }

    @Override
    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
        Expression value = visit(ctx.expr);
        ExpressionUnaryNeg expressionUnaryNeg = new ExpressionUnaryNeg(value);
        expressionUnaryNeg.setToken(ctx.getStart());
        return expressionUnaryNeg;
    }

    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        Expression expression;
        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.EQ:
                expression = new ExpressionComparisonEq(left, right);
                break;
            case QLLexer.NE:
                expression = new ExpressionUnaryNot(new ExpressionComparisonEq(left, right));
                break;
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
        expression.setToken(ctx.getStart());
        return expression;
    }

    @Override
    public Expression visitAndOrExpr(QLParser.AndOrExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        Expression expresionBinary;
        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.AND:
                expresionBinary = new ExpressionLogicalAnd(left, right);
                break;
            case QLLexer.OR:
                expresionBinary = new ExpressionLogicalOr(left, right);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
        expresionBinary.setToken(ctx.getStart());
        return expresionBinary;
    }

    @Override
    public Expression visitBoolExpr(QLParser.BoolExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        ExpressionBinary expressionBinary;
        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.GT:
                expressionBinary = new ExpressionComparisonGT(left, right);
                break;
            case QLLexer.GE:
                expressionBinary = new ExpressionComparisonGE(left, right);
                break;
            case QLLexer.LT:
                expressionBinary = new ExpressionComparisonLT(left, right);
                break;
            case QLLexer.LE:
                expressionBinary = new ExpressionComparisonLE(left, right);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
        expressionBinary.setToken(ctx.getStart());
        return expressionBinary;
    }

    @Override
    public Expression visitParenExpr(QLParser.ParenExprContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Expression visitBooleanConstant(QLParser.BooleanConstantContext ctx) {
        ExpressionVariableBoolean expressionVariableBoolean = new ExpressionVariableBoolean(Boolean.parseBoolean(ctx.getText()));
        expressionVariableBoolean.setToken(ctx.getStart());
        return expressionVariableBoolean;
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        ExpressionVariableInteger expressionVariableInteger = new ExpressionVariableInteger(Integer.parseInt(ctx.getText()));
        expressionVariableInteger.setToken(ctx.getStart());
        return expressionVariableInteger;
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        ExpressionVariableDecimal expressionVariableDecimal = new ExpressionVariableDecimal(Double.parseDouble(ctx.getText()));
        expressionVariableDecimal.setToken(ctx.getStart());
        return expressionVariableDecimal;
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        ExpressionVariableMoney expressionVariableMoney = new ExpressionVariableMoney(ctx.getText());
        expressionVariableMoney.setToken(ctx.getStart());
        return expressionVariableMoney;
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        String text = ctx.getText();

        // remove quotes surrounding the string
        text = text.substring(1, text.length() - 1);

        ExpressionVariableString expressionVariableString = new ExpressionVariableString(text);
        expressionVariableString.setToken(ctx.getStart());
        return expressionVariableString;
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx) {
        ExpressionIdentifier expressionIdentifier = new ExpressionIdentifier(ctx.getText());
        expressionIdentifier.setToken(ctx.getStart());
        return expressionIdentifier;
    }
}
