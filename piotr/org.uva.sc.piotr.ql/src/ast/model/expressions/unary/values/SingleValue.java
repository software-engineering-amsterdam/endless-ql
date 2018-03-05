package ast.model.expressions.unary.values;

import ast.model.expressions.unary.UnaryExpression;
import ast.visitors.ASTNodeVisitor;

public class SingleValue extends UnaryExpression {

    private String value;

    public SingleValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
