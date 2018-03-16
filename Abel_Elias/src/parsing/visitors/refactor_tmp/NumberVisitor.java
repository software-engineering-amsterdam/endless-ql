package parsing.visitors.refactor_tmp;

import classes.Question;
import classes.values.NumericValue;
import parsing.gen.QLParser;
import parsing.visitors.BaseVisitor;

import java.util.HashMap;

public class NumberVisitor extends BaseVisitor {
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

}