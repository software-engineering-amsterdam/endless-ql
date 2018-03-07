package expression.variable;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ReturnType;

public class ExpressionVariableBoolean extends Expression {

    public Boolean value;

    public ExpressionVariableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}