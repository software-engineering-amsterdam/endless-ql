package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;
import expression.ReturnType;

public class ExpressionVariableInteger extends ExpressionVariable<Integer> {

    public ExpressionVariableInteger(Integer value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.INTEGER;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
