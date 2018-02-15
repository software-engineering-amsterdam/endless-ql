package visitor;

import antlr.QLBaseVisitor;
import antlr.QLLexer;
import antlr.QLParser;
import expression.*;
import model.LookupTable;

import java.math.BigDecimal;

public class VisitorExpression extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionNot(value);
    }

    @Override
    public Expression visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        int op = ctx.op.getType();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

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
                throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionNeg(value);
    }

    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        int op = ctx.op.getType();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case QLLexer.EQ:
                return new ExpressionComparisonEq(left, right);
            case QLLexer.NE:
                return new ExpressionNot(new ExpressionComparisonEq(left, right));
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitAndOrExpr(QLParser.AndOrExprContext ctx) {
        int op = ctx.op.getType();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

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
        int op = ctx.op.getType();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

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
    public Expression visitConstant_boolean(QLParser.Constant_booleanContext ctx) {
        return new ExpressionVariableBoolean(Boolean.parseBoolean(ctx.getText()));
    }

    @Override
    public Expression visitConstant_integer(QLParser.Constant_integerContext ctx) {
        // TODO do we have to use integer? what if we do a sum of int + double?
        return new ExpressionVariableInteger(Integer.parseInt(ctx.getText()));
    }

    @Override
    public Expression visitConstant_decimal(QLParser.Constant_decimalContext ctx) {
        return new ExpressionVariableDecimal(Double.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_date(QLParser.Constant_dateContext ctx) {
        return new ExpressionVariableDate(ctx.getText());
    }

    @Override
    public Expression visitConstant_money(QLParser.Constant_moneyContext ctx) {
        // TODO: Same as decimal?
        BigDecimal bigDecimal = BigDecimal.valueOf(Double.valueOf(ctx.getText()));
        return new ExpressionVariableMoney(bigDecimal);
    }

    // TODO do we need this?
//    @Override
//    public Expression visitIdentifier(QLParser.IdentifierContext ctx) {
//        return new ExpressionIdentifier(ctx.getText());
//    }

    @Override
    public Expression visitConstant_string(QLParser.Constant_stringContext ctx) {
        String text = ctx.STRING().toString();
        // remove quotes from text
        text = text.substring(1, text.length() - 1);
        return new ExpressionVariableString(text);
    }

    @Override
    public Expression visitConstant_identifier(QLParser.Constant_identifierContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Expression referenceExpression = LookupTable.getInstance().getQuestionAnswer(identifier);

        switch (referenceExpression.getReturnType()) {
            case Number:
                return new ExpressionIdentifier<Object>(ctx.getText());
            case Boolean:
                return new ExpressionIdentifier<Boolean>(ctx.getText());
            case String:
                return new ExpressionIdentifier<String>(ctx.getText());
            case Money:
                return new ExpressionIdentifier<Double>(ctx.getText());
            case Date:
                return new ExpressionIdentifier<String>(ctx.getText());
            default:
                throw new IllegalArgumentException("identifier '" + identifier + "'does not exist");
        }
    }
}
