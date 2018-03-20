package logic.collectors;

import ast.model.Form;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectQuestionsVisitor extends AbstractASTTraverse<Void> {

    // AST node as key
    private HashMap<Question, List<VariableReference>> questionsMap = new HashMap<>();

    public List<Question> getQuestions(Form form) {
        this.questionsMap = new HashMap<>();
        form.accept(this);
        return new ArrayList<>(this.questionsMap.keySet());
    }

    public HashMap<Question, List<VariableReference>> getQuestionsMap(Form form) {
        this.questionsMap = new HashMap<>();
        form.accept(this);
        return questionsMap;
    }

    @Override
    public Void visit(Question question) {

        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
        List<VariableReference> references = new ArrayList<>();

        if (question.getAssignedExpression() != null) {
            references = collectReferencesVisitor.getVariableReferences(question.getAssignedExpression());
        }

        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }

        this.questionsMap.put(question, references);

        return null;

    }

}