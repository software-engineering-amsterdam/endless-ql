package expression;

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
    public ExpressionVariable divide(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        return this;
    }

    @Override
    public ExpressionVariable not() {
        return this;
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Undefined;
    }
}
