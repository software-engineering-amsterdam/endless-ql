package expression.variable;

import expression.ReturnType;

public class ExpressionVariableUndefined extends ExpressionVariable<Object> {

    public ExpressionVariableUndefined() {
        super(null);
    }

    @Override
    public ExpressionVariable evaluate() {
        return this;
    }

    @Override
    public boolean isSettable() {
        return false;
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.UNDEFINED;
    }

    @Override
    public void setValue(String value) {
        throw new UnsupportedOperationException("Cannot set undefined variable");
    }
}