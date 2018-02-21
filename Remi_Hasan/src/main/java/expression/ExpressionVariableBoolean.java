package expression;

public class ExpressionVariableBoolean extends ExpressionVariable<Boolean> {

    public ExpressionVariableBoolean(Boolean value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Boolean;
    }

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
            this.value = Boolean.parseBoolean(value);
    }

    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value && Boolean.parseBoolean(other.value.toString()));
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value || Boolean.parseBoolean(other.value.toString()));
    }

    @Override
    public ExpressionVariable not() {
        if (this.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(!this.value);
    }
}