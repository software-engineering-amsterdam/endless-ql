package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}