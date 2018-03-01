package visitor;

import antlr.QLBaseVisitor;
import antlr.QLLexer;
import antlr.QLParser;
import expression.*;
import expression.binary.*;
import expression.variable.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import model.LookupTable;
import model.Question;

import java.math.BigDecimal;

public class VisitorExpression extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
        Expression value = visit(ctx.expr);

        if(!value.getReturnType().not())
            throw new IllegalArgumentException("Cannot apply operator not to '" + value.getReturnType() + "'");

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
                if(!left.getReturnType().sum(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator sum to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionArithmeticSum(left, right);
            case QLLexer.MINUS:
                if(!left.getReturnType().subtract(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator subtract to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionArithmeticSubtract(left, right);
            case QLLexer.MUL:
                if(!left.getReturnType().multiply(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator mul to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionArithmeticMultiply(left, right);
            case QLLexer.DIV:
                if(!left.getReturnType().divide(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator divide to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionArithmeticDivide(left, right);
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
    }

    @Override
    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
        Expression value = visit(ctx.expr);

        if(!value.getReturnType().neg())
            throw new IllegalArgumentException("Cannot apply negation on '" + value.getReturnType() + "'");

        return new ExpressionUnaryNeg(value);
    }

    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        int op = ctx.op.getType();

        switch (op) {
            case QLLexer.EQ:
                if(!left.getReturnType().eq(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator eq to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionComparisonEq(left, right);
            case QLLexer.NE:
                if(!left.getReturnType().eq(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator neq to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionUnaryNot(new ExpressionComparisonEq(left, right));
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.op.toString() + "'");
        }
    }

    @Override
    public Expression visitAndOrExpr(QLParser.AndOrExprContext ctx) {
        int op = ctx.op.getType();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case QLLexer.AND:
                if(!left.getReturnType().and(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator and to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionLogicalAnd(left, right);
            case QLLexer.OR:
                if(!left.getReturnType().or(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator or to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

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
                if(!left.getReturnType().gt(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator gt to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionComparisonGT(left, right);
            case QLLexer.GE:
                if(!left.getReturnType().ge(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator ge to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionComparisonGE(left, right);
            case QLLexer.LT:
                if(!left.getReturnType().lt(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator lt to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

                return new ExpressionComparisonLT(left, right);
            case QLLexer.LE:
                if(!left.getReturnType().le(right.getReturnType()))
                    throw new IllegalArgumentException("Cannot apply operator le to '" + left.getReturnType() + "' and '" +  right.getReturnType() + "'");

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
        return new ExpressionVariableNumber(ctx.getText());
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        return new ExpressionVariableNumber(ctx.getText());
    }

    @Override
    public Expression visitDateConstant(QLParser.DateConstantContext ctx) {
        return new ExpressionVariableDate(ctx.getText());
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        return new ExpressionVariableNumber(ctx.getText());
    }

    // TODO do we need this?
//    @Override
//    public Expression visitIdentifier(QLParser.IdentifierContext ctx) {
//        return new ExpressionIdentifier(ctx.getText());
//    }


    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        String text = ctx.STRING().toString();
        // remove quotes from text
        text = text.substring(1, text.length() - 1);
        return new ExpressionVariableString(text);
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Question referencedQuestion = LookupTable.getInstance().getQuestion(identifier);

        switch (referencedQuestion.type) {
            case INTEGER:
                return new ExpressionIdentifier<Integer>(ctx.getText());
            case DECIMAL:
                return new ExpressionIdentifier<Double>(ctx.getText());
            case BOOLEAN:
                return new ExpressionIdentifier<Boolean>(ctx.getText());
            case STRING:
                return new ExpressionIdentifier<String>(ctx.getText());
            case MONEY:
                return new ExpressionIdentifier<Double>(ctx.getText());
            case DATE:
                return new ExpressionIdentifier<String>(ctx.getText());
            default:
                throw new IllegalArgumentException("Cannot create identifier for unknown type '" + referencedQuestion.type + "'");
        }
    }
}
