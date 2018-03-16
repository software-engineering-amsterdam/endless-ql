package parsing.visitors.refactor_tmp;

import classes.Question;
import parsing.checkers.TypeChecker;
import parsing.checkers.VariableChecker;
import parsing.gen.QLParser;

public class InitVisitor extends BaseVisitor {
    // Reads out the initial AST and returns the questions that were found
    public InitVisitor(QLParser.FormContext ctx){
        super(ctx);
        visitForm(ctx);
    }

    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        new VariableChecker(ctx);
        new TypeChecker(ctx);
        visit(ctx.block());

        return questionMap;
    }

    //BOOLEAN EXPRESSION VISITORS
    @Override
    public Boolean visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();
        Question question = getQuestion(id);
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
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Boolean condition = (Boolean) visit(ctx.booleanExpression());

        if(condition){
            visit(ctx.block());
        }

        return questionMap;
    }
}