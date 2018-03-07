package expression.variable;

import astvisitor.BaseASTVisitor;
import expression.ExpressionVariable;

public class ExpressionVariableString extends ExpressionVariable<String> {

    ExpressionVariableString(String value) {
        super(value);
    }

    @Override
    public String accept(BaseASTVisitor<String> visitor) {
        return visitor.visit(this);
    }
}