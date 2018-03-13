package logic.collectors;

import ast.model.expressions.values.VariableReference;
import ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;

public class CollectReferencesVisitor extends AbstractASTTraverse<Void> {

    private ArrayList<VariableReference> variableReferences = new ArrayList<>();

    public ArrayList<VariableReference> getVariableReferences() {
        return variableReferences;
    }

    @Override
    public Void visit(VariableReference variableReference) {
        this.variableReferences.add(variableReference);
        return null;
    }
}
