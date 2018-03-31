package ql.builder;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.model.expression.Expression;
import ql.model.expression.Identifier;
import ql.model.expression.binary.*;
import ql.model.expression.constant.*;
import ql.model.expression.unary.NegationExpression;
import ql.model.expression.unary.NotExpression;

public class ExpressionBuilder extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitNotExpression(QLParser.NotExpressionContext ctx) {
        Expression value = visit(ctx.expression());
        NotExpression notExpression = new NotExpression(value);
        notExpression.setToken(ctx.getStart());
        return notExpression;
    }

    @Override
    public Expression visitArithmeticExpression(QLParser.ArithmeticExpressionContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        BinaryExpression binaryExpression;
        int op = ctx.operator.getType();
        switch (op) {
            case QLLexer.PLUS:
                binaryExpression = new SumExpression(left, right);
                break;
            case QLLexer.MINUS:
                binaryExpression = new SubtractionExpression(left, right);
                break;
            case QLLexer.MUL:
                binaryExpression = new MultiplicationExpression(left, right);
                break;
            case QLLexer.DIV:
                binaryExpression = new DivisionExpression(left, right);
                break;
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.operator.toString() + "'");
        }
        binaryExpression.setToken(ctx.getStart());
        return binaryExpression;
    }

    @Override
    public Expression visitNegationExpression(QLParser.NegationExpressionContext ctx) {
        Expression value = visit(ctx.expression());
        NegationExpression negationExpression = new NegationExpression(value);
        negationExpression.setToken(ctx.getStart());
        return negationExpression;
    }

    @Override
    public Expression visitComparisonExpression(QLParser.ComparisonExpressionContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        Expression expression;
        int op = ctx.operator.getType();
        switch (op) {
            case QLLexer.EQ:
                expression = new EqualExpression(left, right);
                break;
            case QLLexer.NE:
                expression = new NotExpression(new EqualExpression(left, right));
                break;
            default:
                throw new IllegalArgumentException("Cannot apply unknown operator '" + ctx.operator.toString() + "'");
        }
        expression.setToken(ctx.getStart());
        return expression;
    }

    @Override
    public Expression visitAndOrExpression(QLParser.AndOrExpressionContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        Expression expresionBinary;
        int op = ctx.operator.getType();
        switch (op) {
            case QLLexer.AND:
                expresionBinary = new AndExpression(left, right);
                break;
            case QLLexer.OR:
                expresionBinary = new OrExpression(left, right);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
        expresionBinary.setToken(ctx.getStart());
        return expresionBinary;
    }

    @Override
    public Expression visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        BinaryExpression binaryExpression;
        int op = ctx.operator.getType();
        switch (op) {
            case QLLexer.GT:
                binaryExpression = new GreaterThanExpression(left, right);
                break;
            case QLLexer.GE:
                binaryExpression = new GreaterEqualExpression(left, right);
                break;
            case QLLexer.LT:
                binaryExpression = new LessThanExpression(left, right);
                break;
            case QLLexer.LE:
                binaryExpression = new LessEqualExpression(left, right);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
        binaryExpression.setToken(ctx.getStart());
        return binaryExpression;
    }

    @Override
    public Expression visitParenthesesExpression(QLParser.ParenthesesExpressionContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Expression visitBooleanConstant(QLParser.BooleanConstantContext ctx) {
        BooleanConstant booleanConstant = new BooleanConstant(Boolean.parseBoolean(ctx.getText()));
        booleanConstant.setToken(ctx.getStart());
        return booleanConstant;
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        IntegerConstant integerConstantExpression = new IntegerConstant(Integer.parseInt(ctx.getText()));
        integerConstantExpression.setToken(ctx.getStart());
        return integerConstantExpression;
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        DecimalConstant decimalConstantExpression = new DecimalConstant(Double.parseDouble(ctx.getText()));
        decimalConstantExpression.setToken(ctx.getStart());
        return decimalConstantExpression;
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        MoneyConstant moneyConstantExpression = new MoneyConstant(ctx.getText());
        moneyConstantExpression.setToken(ctx.getStart());
        return moneyConstantExpression;
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        String text = ctx.getText();

        // remove quotes surrounding the string
        text = text.substring(1, text.length() - 1);

        StringConstant stringConstant = new StringConstant(text);
        stringConstant.setToken(ctx.getStart());
        return stringConstant;
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx) {
        Identifier identifier = new Identifier(ctx.getText());
        identifier.setToken(ctx.getStart());
        return identifier;
    }
}
