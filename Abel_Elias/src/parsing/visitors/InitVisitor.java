package parsing.visitors;

import classes.CodeBlock;
import classes.Question;
import com.sun.org.apache.xpath.internal.operations.Bool;
import parsing.checkers.TypeChecker;
import parsing.checkers.VariableChecker;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

import java.util.HashMap;

public class InitVisitor extends baseVisitor {
    public InitVisitor(QLParser.FormContext ctx){
        super();
        visitBlock(ctx.block());
    }

    // Node visitor
    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        new VariableChecker(questionMap, ctx.block());
        new TypeChecker(questionMap, ctx.block());

        return visitChildren(ctx);
    }

    @Override
    public Boolean visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();
        Question question = getQuestion(id);
        return (Boolean) question.getValue();
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
        Double left = (Double) visit(ctx.left);
        String operator = ctx.comparisonOperator().getText();
        Double right = (Double) visit(ctx.right);

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
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Boolean input = (Boolean) visit(ctx.booleanExpression());

        if(input){
            visit(ctx.block());
        }

        return questionMap;
    }
}