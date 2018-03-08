package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;

public class ExpressionVariableDate extends ExpressionVariable<String> {

    public ExpressionVariableDate(String value) {
        super(value);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}