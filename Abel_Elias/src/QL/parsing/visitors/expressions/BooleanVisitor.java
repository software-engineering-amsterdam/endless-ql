package QL.parsing.visitors.expressions;

import QL.classes.Question;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;

import java.util.HashMap;

public class BooleanVisitor extends QLBaseVisitor {
    HashMap<String, Question> questionMap;
    NumberVisitor  numberVisitor;

    public BooleanVisitor(HashMap<String, Question> questionMap, NumberVisitor  numberVisitor){
        this.questionMap = questionMap;
        this.numberVisitor = numberVisitor;
    }

    public Boolean visitBoolExpression(QLParser.BooleanExpressionContext ctx){
        return (Boolean) visit(ctx);
    }

    @Override
    public Boolean visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();
        Question question = questionMap.get(id);
        return (Boolean) question.getValue().getValue();
    }

    @Override
    public Boolean visitBoolBraces(QLParser.BoolBracesContext ctx) {
        return (Boolean) visit(ctx.booleanExpression());
    }

    @Override
    public Boolean visitNotOperation(QLParser.NotOperationContext ctx) {
        return !((Boolean) visit(ctx.booleanExpression()));
    }

    @Override
    public Boolean visitBoolOperation(QLParser.BoolOperationContext ctx) {
        Boolean left = (Boolean) visit(ctx.left);
        String operator = ctx.boolOperator().getText();
        Boolean right = (Boolean) visit(ctx.right);

        switch (operator) {
            case "&&":
                return left && right;
            case "||":
                return left || right;
            case "!=":
                return left != right;
            case "==":
                return left == right;
        }

        return null;
    }

    @Override
    public Boolean visitCompOperation(QLParser.CompOperationContext ctx) {
        double left = numberVisitor.visitNumberExpression(ctx.left);
        String operator = ctx.comparisonOperator().getText();
        double right = numberVisitor.visitNumberExpression(ctx.right);

        switch (operator) {
            case "<":
                return left < right;
            case ">":
                return left > right;
            case "!=":
                return left != right;
            case "==":
                return left == right;
        }

        return null;
    }

    @Override
    public Boolean visitBoolValue(QLParser.BoolValueContext ctx) {
        return Boolean.parseBoolean(ctx.getText());
    }
}