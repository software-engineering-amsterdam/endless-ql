package QL.parsing.visitors;

import QL.classes.Question;
import QL.classes.values.NumericValue;
import QL.classes.values.Value;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ExpressionVisitor extends QLBaseVisitor {
    private LinkedHashMap<String, Question> questionMap;

    public ExpressionVisitor(LinkedHashMap<String, Question> questionMap){
        this.questionMap = questionMap;
    }

    public Object visitExpression(QLParser.ExpressionContext ctx){
        return visit(ctx);
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx) {
        Value value = questionMap.get(ctx.IDENTIFIER().getText()).getValue();

        if(value instanceof NumericValue){
            return ((NumericValue) value).getComputationValue();
        }else{
            return value.getValue();
        }

    }

    @Override
    public Boolean visitEqExpression(QLParser.EqExpressionContext ctx) {
        Object left =  visitExpression(ctx.left);
        Object right = visitExpression(ctx.right);
        String operator = ctx.equalsOperator().getText();

        switch (operator){
            case "==":
                return left.equals(right);
            case "!=":
                return !left.equals(right);
        }

        return null;
    }

    @Override
    public Boolean visitBoolExpression(QLParser.BoolExpressionContext ctx) {
        Boolean left = (boolean) visitExpression(ctx.left);
        Boolean right = (boolean) visitExpression(ctx.right);
        String operator = ctx.boolOperator().getText();

        switch (operator){
            case "&&":
                return left && right;
            case "!=":
                return left || right;
        }

        return null;
    }

    @Override
    public Boolean visitCompExpression(QLParser.CompExpressionContext ctx) {
        Double left = (double) visitExpression(ctx.left);
        Double right = (double) visitExpression(ctx.right);
        String operator = ctx.comparisonOperator().getText();

        switch (operator){
            case ">":
                return left > right;
            case "<":
                return left < right;
            case ">=":
                return left >= right;
            case "<=":
                return left <= right;
        }

        return null;
    }

    @Override
    public Double visitNumExpression(QLParser.NumExpressionContext ctx) {
        Double left = (double) visitExpression(ctx.left);
        Double right = (double) visitExpression(ctx.right);
        String operator = ctx.numberOperator().getText();

        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            case "^":
                return Math.pow(left,right);
            case "%":
                return left % right;
        }

        return null;
    }

    @Override
    public Object visitBraceExpression(QLParser.BraceExpressionContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override
    public Boolean visitNotExpression(QLParser.NotExpressionContext ctx) {
        Boolean value = (boolean) visitExpression(ctx.expression());
        return !value;
    }

    @Override
    public Boolean visitBoolValue(QLParser.BoolValueContext ctx) {
        return Boolean.parseBoolean(ctx.getText());
    }

    @Override
    public String visitStrValue(QLParser.StrValueContext ctx) {
        return ctx.getText();
    }

    @Override
    public Double visitNumValue(QLParser.NumValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }
}