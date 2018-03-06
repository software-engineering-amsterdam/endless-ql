package ast.visitors;

import ast.model.expressions.unary.values.VariableReference;
import ast.model.statement.Question;

import java.util.ArrayList;
import java.util.Objects;

public class ReferencesList extends ASTNodeAbstractVisitor {

    private ArrayList<VariableReference> variableReferences = new ArrayList<>();

    public ArrayList<VariableReference> getVariableReferences() {
        return variableReferences;
    }

    @Override
    public void visit(VariableReference variableReference) {
        this.variableReferences.add(variableReference);
        super.visit(variableReference);
    }

    public void validateWithGraph(QuestionsList graph) throws RuntimeException {
        for (VariableReference reference : this.variableReferences) {
            boolean found = false;
            for (Question question : graph.getQuestions()) {

                if (Objects.equals(question.getVariableName(), reference.getName())) {
                    found = true;
                }
            }
            if (!found) {
                throw new RuntimeException("Reference to undeclared variable \"" + reference.getName() + "\" on line " + reference.getStartLine() + ".");
            }
        }
    }
}
