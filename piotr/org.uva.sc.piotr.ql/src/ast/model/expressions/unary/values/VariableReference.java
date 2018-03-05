package ast.model.expressions.unary.values;

import ast.model.expressions.unary.UnaryExpression;
import ast.visitors.ASTNodeVisitor;

public class VariableReference extends UnaryExpression {

    private String name;

    public VariableReference(String name, Integer startLine, Integer endLine) {
        super(startLine, endLine);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
