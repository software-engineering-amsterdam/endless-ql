package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;
import expression.ReturnType;

public class ExpressionVariableDate extends ExpressionVariable<String> {

    public ExpressionVariableDate(String value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.DATE;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}