package expression.variable;

import astvisitor.BaseASTVisitor;
import expression.ExpressionVariable;

public class ExpressionVariableDate extends ExpressionVariable<String> {

    ExpressionVariableDate(String value) {
        super(value);
    }

    @Override
    public String accept(BaseASTVisitor<String> visitor) {
        return visitor.visit(this);
    }
}