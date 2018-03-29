package ql.model.expression.variable;

import ql.IQLVisitor;
import ql.model.expression.ReturnType;

public class ExpressionVariableUndefined extends ExpressionVariable<ReturnType> {

    public ExpressionVariableUndefined(ReturnType value) {
        super(value);
    }

    public ReturnType getReturnType() {
        return this.value;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}