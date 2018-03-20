package ql.logic.collectors;

import ql.ast.model.Form;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.ast.visitors.AbstractASTTraverse;

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
