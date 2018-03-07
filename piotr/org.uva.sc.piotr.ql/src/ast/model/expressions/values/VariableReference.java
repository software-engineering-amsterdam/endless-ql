package ast.model.expressions.values;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class VariableReference extends Expression {

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
    public void accept(ASTNodeVisitor visitor) {}

}
