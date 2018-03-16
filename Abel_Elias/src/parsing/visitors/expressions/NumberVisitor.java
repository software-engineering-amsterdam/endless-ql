package parsing.visitors.expressions;

import classes.Question;
import classes.values.NumericValue;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

import java.util.HashMap;

public class NumberVisitor extends QLBaseVisitor {
    HashMap<String, Question> questionMap;

    public NumberVisitor(HashMap<String, Question> questionMap){
        this.questionMap = questionMap;
    }

    public double visitNumberExpression(QLParser.NumberExpressionContext ctx){
        return (double) visit(ctx);
    }

    @Override
    public Double visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
        Question q = questionMap.get(ctx.IDENTIFIER().getText());
        return ((NumericValue) q.getValue()).getComputationValue();
    }

    @Override
    public Double visitNumBraces(QLParser.NumBracesContext ctx) {
        return (double) visit(ctx.numberExpression());
    }

    @Override
    public Number visitNumOperation(QLParser.NumOperationContext ctx) {
        Double left = (double) visit(ctx.left);
        String operator = ctx.numberOperator().getText();
        Double right = (double) visit(ctx.right);

        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
        }

        return null;
    }

    @Override
    public Double visitIntValue(QLParser.IntValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitMoneyValue(QLParser.MoneyValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitDecValue(QLParser.DecValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }
}