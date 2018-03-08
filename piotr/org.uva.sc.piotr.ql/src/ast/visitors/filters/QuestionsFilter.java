package ast.visitors.filters;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionsFilter extends AbstractASTTraverse<Void> {

    // AST node as key
    private HashMap<Question, ArrayList<VariableReference>> questionsMap = new HashMap<>();

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(this.questionsMap.keySet());
    }

    public HashMap<Question, ArrayList<VariableReference>> getQuestionsMap() {
        return questionsMap;
    }

    @Override
    public Void visit(Question question) {
//
//        ReferencesFilter collectReferencesVisitor = new ReferencesFilter();
//        question.getAssignedExpression().accept(collectReferencesVisitor);
//
//        ArrayList<VariableReference> references = collectReferencesVisitor.getVariableReferences();
//
//        if (question.getAssignedExpression() != null) {
//            question.getAssignedExpression().accept(this);
//        }
//
//        this.questionsMap.put(question, references);

        return null;

    }

}
