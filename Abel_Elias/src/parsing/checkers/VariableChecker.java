package parsing.checkers;

import classes.Question;
import parsing.errors.DupVarError;
import parsing.errors.UndeclaredError;
import parsing.gen.QLParser;
import parsing.visitors.baseVisitor;

import java.util.HashMap;

public class VariableChecker extends baseVisitor {
    // The variable checker checks if there are any duplicate variables, or references to variables that do not exist

    public VariableChecker(HashMap<String, Question> questionMap, QLParser.BlockContext ctx){
        super();
        addQuestions(questionMap);
        visitChildren(ctx);
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        if(containsQuestion(id)){
            throw new DupVarError(id);
        }

        return super.visitNormalQuestion(ctx);
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        if(containsQuestion(id)){
            throw new DupVarError(id);
        }

        return super.visitFixedQuestion(ctx);
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
       return checkVariableExistence(ctx.getText());
    }

    @Override
    public Object visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
        return checkVariableExistence(ctx.getText());
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx) {
        return checkVariableExistence(ctx.getText());
    }

    @Override
    public Object visitBlock(QLParser.BlockContext ctx) {
        return new VariableChecker(this.getQuestions(), ctx);
    }

    private Question checkVariableExistence(String id){
        if(!getQuestions().containsKey(id)) {
            throw new UndeclaredError(id);
        }

        return getQuestions().get(id);
    }
}