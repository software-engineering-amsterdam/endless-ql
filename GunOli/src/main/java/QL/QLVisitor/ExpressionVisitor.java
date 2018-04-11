package QL.QLVisitor;

import QL.AST.Expressions.BinaryExpressions.*;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.*;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Expressions.UnaryExpressions.NegationExpression;
import QL.AST.Expressions.UnaryExpressions.NotExpression;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpressionVisitor extends QLBaseVisitor<Expression> {
    @Override
    public Expression visitBinaryExpr(QLParser.BinaryExprContext ctx) {
        int line = ctx.getStart().getLine();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);
        String operator = ctx.operator().getText();

        switch(operator){
            case "+" : return new AdditionExpression(left, right, line);
            case "-" : return new SubtractExpression(left, right, line);
            case "*" : return new MultiplicationExpression(left, right, line);
            case "/" : return new DivisionExpression(left, right, line);
            case ">" : return new GreaterThanExpression(left, right, line);
            case ">=": return new GreaterOrEqualExpression(left,right,line);
            case "<" : return new LessThanExpression(left, right, line);
            case "<=": return new LessOrEqualExpression(left, right, line);
            case "==": return new EqualExpression(left, right, line);
            case "!=": return new NotEqualExpression(left, right, line);
            case "&&": return new AndExpression(left, right, line);
            case "||": return new OrExpression(left, right, line);
            default: throw new IllegalArgumentException("Unable to parse '"+ operator +"': unregonized operator");
        }
    }

    @Override
    public Expression visitUnaryExpr(QLParser.UnaryExprContext ctx) {
        int line = ctx.getStart().getLine();
        Expression expr = visit(ctx.expression());
        String operator = ctx.operator().getText();

        if (operator.charAt(0) == '-') {
            return new NegationExpression(expr, line);
        }
        else if (operator.charAt(0) == '!') {
            return new NotExpression(expr, line);
        }
        throw new IllegalArgumentException("Unable to parse '"+ operator +"': unregonized operator");
    }

    @Override
    public Expression visitNestedExpr(QLParser.NestedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        return new StringConstant(ctx.getText(), ctx.getStart().getLine());
    }

    @Override
    public Expression visitDateConstant(QLParser.DateConstantContext ctx) {
        String dateString = ctx.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return new DateConstant(localDate, ctx.getStart().getLine());
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        return new IntegerConstant(Integer.parseInt(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        return new DecimalConstant(Double.parseDouble(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        return new MoneyConstant(Double.parseDouble(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitBooleanConstant(QLParser.BooleanConstantContext ctx){
        return new BooleanConstant(Boolean.parseBoolean(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx){
        return new IdentifierExpression(ctx.IDENTIFIER().getText(), ctx.getStart().getLine());
    }
}