package ql.visitor;

import ql.parser.QLBaseVisitor;
import ql.parser.QLLexer;
import ql.parser.QLParser;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitorExpression extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionUnaryNot(ctx.getStart(), value);
    }

    @Override
    public Expression visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.PLUS:
                return new ExpressionArithmeticSum(ctx.getStart(), left, right);
            case QLLexer.MINUS:
                return new ExpressionArithmeticSubtract(ctx.getStart(), left, right);
            case QLLexer.MUL:
                return new ExpressionArithmeticMultiply(ctx.getStart(), left, right);
            case QLLexer.DIV:
                return new ExpressionArithmeticDivide(ctx.getStart(), left, right);
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
    }

    @Override
    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionUnaryNeg(ctx.getStart(), value);
    }

    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.EQ:
                return new ExpressionComparisonEq(ctx.getStart(), left, right);
            case QLLexer.NE:
                return new ExpressionUnaryNot(ctx.getStart(), new ExpressionComparisonEq(ctx.getStart(), left, right));
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
    }

    @Override
    public Expression visitAndOrExpr(QLParser.AndOrExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.AND:
                return new ExpressionLogicalAnd(ctx.getStart(), left, right);
            case QLLexer.OR:
                return new ExpressionLogicalOr(ctx.getStart(), left, right);
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitBoolExpr(QLParser.BoolExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.GT:
                return new ExpressionComparisonGT(ctx.getStart(), left, right);
            case QLLexer.GE:
                return new ExpressionComparisonGE(ctx.getStart(), left, right);
            case QLLexer.LT:
                return new ExpressionComparisonLT(ctx.getStart(), left, right);
            case QLLexer.LE:
                return new ExpressionComparisonLE(ctx.getStart(), left, right);
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitParenExpr(QLParser.ParenExprContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Expression visitBooleanConstant(QLParser.BooleanConstantContext ctx) {
        return new ExpressionVariableBoolean(ctx.getStart(), Boolean.parseBoolean(ctx.getText()));
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        return new ExpressionVariableInteger(ctx.getStart(), Integer.parseInt(ctx.getText()));
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        return new ExpressionVariableDecimal(ctx.getStart(), Double.parseDouble(ctx.getText()));
    }

    @Override
    public Expression visitDateConstant(QLParser.DateConstantContext ctx) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        Date date;
        try {
            date = dateFormatter.parse(ctx.getText());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date passed");
        }

        return new ExpressionVariableDate(ctx.getStart(), date);
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        return new ExpressionVariableMoney(ctx.getStart(), ctx.getText());
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        String text = ctx.STRING().toString();

        // remove quotes surrounding the string
        text = text.substring(1, text.length() - 1);

        return new ExpressionVariableString(ctx.getStart(), text);
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx) {
        return new ExpressionIdentifier(ctx.getStart(), ctx.IDENTIFIER().getText());
    }
}
