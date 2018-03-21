package ql.ast.model.expressions.values;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class VariableReference extends Expression {

    private final String name;

    public VariableReference(String name, MetaInformation metaInformation) {
        super(metaInformation);
        this.name = name;
    }

    public VariableReference(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
