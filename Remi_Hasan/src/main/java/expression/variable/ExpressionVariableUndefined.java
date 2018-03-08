package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;
import expression.ReturnType;

public class ExpressionVariableUndefined extends ExpressionVariable<ReturnType> {

    public ExpressionVariableUndefined(ReturnType value) {
        super(value);
    }

    public ReturnType getReturnType() {
        if(this.value == ReturnType.INTEGER || this.value == ReturnType.DECIMAL || this.value == ReturnType.MONEY) {
            return ReturnType.NUMBER;
        }

        return this.value;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}