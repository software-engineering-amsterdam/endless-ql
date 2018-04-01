package QL.parsing.checkers;

import QL.parsing.checkers.errors.DuplicateVarError;
import QL.parsing.checkers.errors.UndeclaredVarError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class VariableChecker extends QLBaseVisitor {
    private LinkedHashMap<String, String> variableMap;

    VariableChecker(){
        this.variableMap = new LinkedHashMap<>();
    }

    public void checkForm(QLParser.FormContext form){
        visitForm(form);
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();
        checkVariableDuplication(id, type);

        this.variableMap.put(id,type);
        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx){
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();
        checkVariableDuplication(id, type);
        visit(ctx.expression());

        this.variableMap.put(id,type);
        return true;
    }

    @Override
    public Boolean visitIdentifier(QLParser.IdentifierContext ctx)  {
        checkVariableExistence(ctx.getText());
        return true;
    }

    @Override
    public Boolean visitIfStatement(QLParser.IfStatementContext ctx) {
        LinkedHashMap<String, String> backtrack = new LinkedHashMap<>();
        backtrack.putAll(variableMap);
        visit(ctx.expression());

        visitBlock(ctx.ifBlock);
        if(ctx.elseBlock != null){
            visitBlock(ctx.elseBlock);
        }

        this.variableMap = backtrack;
        return true;
    }

    private void checkVariableDuplication(String id, String type) {
        if(variableMap.containsKey(id) && !variableMap.get(id).equals(type)){
            throw new DuplicateVarError(id);
        }
    }

    private void checkVariableExistence(String id) {
        if(!variableMap.containsKey(id)) {
            throw new UndeclaredVarError(id);
        }
    }
}