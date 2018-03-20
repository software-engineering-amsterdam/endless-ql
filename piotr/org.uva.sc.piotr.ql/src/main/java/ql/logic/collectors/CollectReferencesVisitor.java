package ql.logic.collectors;

import ql.ast.model.ASTNode;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.visitors.AbstractASTTraverse;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CollectReferencesVisitor extends AbstractASTTraverse<Void> {

    private List<VariableReference> variableReferences = new ArrayList<>();

    public List<VariableReference> getVariableReferences(@NotNull ASTNode node) {
        variableReferences = new ArrayList<>();
        node.accept(this);
        return this.variableReferences;
    }

    @Override
    public Void visit(VariableReference variableReference) {
        this.variableReferences.add(variableReference);
        return null;
    }
}
