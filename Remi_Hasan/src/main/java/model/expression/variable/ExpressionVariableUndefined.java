package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.ExpressionVariable;
import model.expression.ReturnType;

public class ExpressionVariableUndefined extends ExpressionVariable<ReturnType> {

    public ExpressionVariableUndefined(ReturnType value) {
        super(value);
    }

    public ReturnType getReturnType() {
        if (this.value.isNumber()) {
            return ReturnType.NUMBER;
        }

        return this.value;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}