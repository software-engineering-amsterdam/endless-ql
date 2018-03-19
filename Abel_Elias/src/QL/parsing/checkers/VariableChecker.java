package QL.parsing.checkers;

import QL.classes.Question;
import QL.parsing.checkers.errors.DuplicateVarError;
import QL.parsing.checkers.errors.UndeclaredVarError;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.refactor_tmp.BaseVisitor;

import java.util.HashMap;

public class VariableChecker extends BaseVisitor {
    // The variable checker checks if there are any duplicate variables, or references to variables that do not exist
    public VariableChecker(QLParser.FormContext ctx){
        initQuestionMap();
        visitBlock(ctx.block());
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        checkVariableDuplication(id);

        addQuestion(id, null);
        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx){
        String id = ctx.IDENTIFIER().getText();
        checkVariableDuplication(id);

        addQuestion(id, null);
        return true;
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
       checkVariableExistence(ctx.getText());
       return super.visitBoolIdentifier(ctx);
    }

    @Override
    public Double visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
        checkVariableExistence(ctx.getText());
        return 0.0;
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx)  {
        checkVariableExistence(ctx.getText());
        return super.visitIdentifier(ctx);
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        HashMap<String, Question> backtrack = new HashMap<>(getQuestions());

        visit(ctx.ifBlock);
        if(ctx.elseBlock != null){
            visit(ctx.elseBlock);
        }

        setQuestionMap(backtrack);
        return true;
    }

    private void checkVariableDuplication(String id) {
        if(containsQuestion(id)){
            throw new DuplicateVarError(id);
        }
    }

    private void checkVariableExistence(String id) {
        if(!getQuestions().containsKey(id)) {
            throw new UndeclaredVarError(id);
        }
    }
}