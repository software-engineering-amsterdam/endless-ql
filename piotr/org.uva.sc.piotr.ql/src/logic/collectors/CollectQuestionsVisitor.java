package logic.collectors;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectQuestionsVisitor extends AbstractASTTraverse<Void> {

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

        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(collectReferencesVisitor);
        }

        ArrayList<VariableReference> references = collectReferencesVisitor.getVariableReferences();

        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }

        this.questionsMap.put(question, references);

        return null;

    }

}
