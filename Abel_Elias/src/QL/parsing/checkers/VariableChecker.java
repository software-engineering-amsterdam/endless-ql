package QL.parsing.checkers;

import QL.parsing.checkers.errors.DuplicateVarError;
import QL.parsing.checkers.errors.UndeclaredVarError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import java.util.ArrayList;
import java.util.HashMap;

public class VariableChecker extends QLBaseVisitor {
    private HashMap<String, String> variableList;

    public VariableChecker(){
        variableList = new HashMap();
    }

    public void checkForm(QLParser.FormContext form){
        visitForm(form);
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();
        checkVariableDuplication(id, type);

        this.variableList.put(id, type);
        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx){
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();
        checkVariableDuplication(id, type);
        visit(ctx.expression());

        this.variableList.put(id, type);
        return true;
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx)  {
        checkVariableExistence(ctx.getText());
        return super.visitIdentifier(ctx);
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        HashMap<String, String> backtrack = new HashMap();
        backtrack.putAll(this.variableList);

        visit(ctx.expression());
        visitBlock(ctx.ifBlock);
        if(ctx.elseBlock != null){
            visitBlock(ctx.elseBlock);
        }

        this.variableList = backtrack;
        return true;
    }

    private void checkVariableExistence(String id) {
        if(!variableList.containsKey(id)) {
            throw new UndeclaredVarError(id);
        }
    }

    private void checkVariableDuplication(String id, String type){
        if(variableList.containsKey(id) && !variableList.get(id).equals(type)){
            throw new DuplicateVarError(id);
        }
    }
}