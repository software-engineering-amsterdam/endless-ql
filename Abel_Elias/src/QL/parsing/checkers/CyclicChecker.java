package QL.parsing.checkers;

import QL.classes.Question;
import QL.parsing.checkers.errors.CyclicError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CyclicChecker extends QLBaseVisitor{
    private HashMap<String, ArrayList<String>> pointerMap;
    private String currentQuestion;

    CyclicChecker(){
        pointerMap = new HashMap();
    }

    public void checkForm(QLParser.FormContext form){
        visit(form);
        checkForCyclicDependencies();
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        ArrayList<String> pointers = new ArrayList();
        pointerMap.put(id, pointers);
        currentQuestion = id;

        return visit(ctx.expression());
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx) {
        return pointerMap.get(currentQuestion).addAll(pointerMap.get(ctx.getText()));
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
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
