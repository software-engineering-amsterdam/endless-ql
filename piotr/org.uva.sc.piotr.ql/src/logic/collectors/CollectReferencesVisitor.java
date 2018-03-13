package logic.collectors;

import ast.model.expressions.values.VariableReference;
import ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;
import java.util.List;

public class CollectReferencesVisitor extends AbstractASTTraverse<Void> {

    private List<VariableReference> variableReferences = new ArrayList<>();

    public List<VariableReference> getVariableReferences() {
        return variableReferences;
    }

    // TODO: This reset is ugly - refactor?
    public void reset() {
        this.variableReferences = new ArrayList<>();
    }

    @Override
    public Void visit(VariableReference variableReference) {
        this.variableReferences.add(variableReference);
        return null;
    }
}
