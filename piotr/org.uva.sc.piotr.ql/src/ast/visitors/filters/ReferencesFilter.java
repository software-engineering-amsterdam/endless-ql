package ast.visitors.filters;

import ast.model.expressions.unary.values.VariableReference;
import ast.visitors.ASTNodeAbstractVisitor;

import java.util.ArrayList;

public class ReferencesFilter extends ASTNodeAbstractVisitor {

    private ArrayList<VariableReference> variableReferences = new ArrayList<>();

    public ArrayList<VariableReference> getVariableReferences() {
        return variableReferences;
    }

    @Override
    public void visit(VariableReference variableReference) {
        this.variableReferences.add(variableReference);
        super.visit(variableReference);
    }
}
