package visitor;

import antlr.QLBaseVisitor;
import antlr.QLLexer;
import antlr.QLParser;
import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;

public class VisitorExpression extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionUnaryNot(value);
    }

    @Override
    public Expression visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.PLUS:
                return new ExpressionArithmeticSum(left, right);
            case QLLexer.MINUS:
                return new ExpressionArithmeticSubtract(left, right);
            case QLLexer.MUL:
                return new ExpressionArithmeticMultiply(left, right);
            case QLLexer.DIV:
                return new ExpressionArithmeticDivide(left, right);
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
    }

    @Override
    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionUnaryNeg(value);
    }

    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();
        switch (op) {
            case QLLexer.EQ:
                return new ExpressionComparisonEq(left, right);
            case QLLexer.NE:
                return new ExpressionUnaryNot(new ExpressionComparisonEq(left, right));
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
                return new ExpressionLogicalAnd(left, right);
            case QLLexer.OR:
                return new ExpressionLogicalOr(left, right);
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
                return new ExpressionComparisonGT(left, right);
            case QLLexer.GE:
                return new ExpressionComparisonGE(left, right);
            case QLLexer.LT:
                return new ExpressionComparisonLT(left, right);
            case QLLexer.LE:
                return new ExpressionComparisonLE(left, right);
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
        return new ExpressionVariableBoolean(Boolean.parseBoolean(ctx.getText()));
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        return new ExpressionVariableInteger(Integer.parseInt(ctx.getText()));
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        return new ExpressionVariableDecimal(Double.parseDouble(ctx.getText()));
    }

    @Override
    public Expression visitDateConstant(QLParser.DateConstantContext ctx) {
        return new ExpressionVariableDate(ctx.getText());
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        return new ExpressionVariableMoney(ctx.getText());
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        String text = ctx.STRING().toString();

        // remove quotes surrounding the string
        text = text.substring(1, text.length() - 1);

        return new ExpressionVariableString(text);
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx) {
        return new ExpressionIdentifier(ctx.IDENTIFIER().getText());
    }
}
