package QL.parsing.checkers;

import QL.parsing.checkers.errors.CyclicError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CyclicChecker extends QLBaseVisitor{
    private HashMap<String, ArrayList<String>> pointerMap;
    private String currentQuestion;

    public CyclicChecker(){
        pointerMap = new HashMap();
    }

    public void checkForm(QLParser.FormContext form){
        visit(form);
        checkForCyclicDependencies();
    }

    @Override
    public Boolean visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        currentQuestion = ctx.IDENTIFIER().getText();

        if(!pointerMap.containsKey(currentQuestion)){
            ArrayList<String> pointers = new ArrayList();
            pointerMap.put(currentQuestion, pointers);
        }

        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        currentQuestion = ctx.IDENTIFIER().getText();

        if(!pointerMap.containsKey(currentQuestion)){
            ArrayList<String> pointers = new ArrayList();
            pointerMap.put(currentQuestion, pointers);
        }

        return visit(ctx.expression());
    }

    @Override
    public Boolean visitIdentifier(QLParser.IdentifierContext ctx) {
        String id = ctx.getText();
        pointerMap.get(currentQuestion).add(id);
        pointerMap.get(currentQuestion).addAll(pointerMap.get(id));

        return true;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        // Stop traversing through the AST at if-statement
        return null;
    }

    private void checkForCyclicDependencies(){
        String current;
        ArrayList<String> pointers;

        for (Map.Entry e : pointerMap.entrySet()) {
            current = (String) e.getKey();
            pointers = (ArrayList<String>) e.getValue();

            for(String pointer : pointers){
                if(pointerMap.get(pointer).contains(current)){
                    throw new CyclicError(current, pointer);
                }
            }
        }
    }
}
