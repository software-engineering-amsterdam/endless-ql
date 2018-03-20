package QL.parsing.checkers;

import QL.parsing.checkers.errors.DuplicateVarError;
import QL.parsing.checkers.errors.UndeclaredVarError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import java.util.ArrayList;

public class VariableChecker extends QLBaseVisitor {
    private ArrayList<String> variableList;

    public VariableChecker(){
        variableList = new ArrayList();
    }

    public void checkForm(QLParser.FormContext form){
        visitForm(form);
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        checkVariableDuplication(id);

        this.variableList.add(id);
        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx){
        String id = ctx.IDENTIFIER().getText();
        checkVariableDuplication(id);
        visit(ctx.expression());

        this.variableList.add(id);
        return true;
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx)  {
        checkVariableExistence(ctx.getText());
        return super.visitIdentifier(ctx);
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        ArrayList<String> backtrack = new ArrayList();
        backtrack.addAll(variableList);
        visit(ctx.expression());

        visitBlock(ctx.ifBlock);
        if(ctx.elseBlock != null){
            visitBlock(ctx.elseBlock);
        }

        this.variableList = backtrack;
        return true;
    }

    private void checkVariableDuplication(String id) {
        if(variableList.contains(id)){
            throw new DuplicateVarError(id);
        }
    }

    private void checkVariableExistence(String id) {
        if(!variableList.contains(id)) {
            throw new UndeclaredVarError(id);
        }
    }
}