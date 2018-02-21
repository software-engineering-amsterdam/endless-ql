package expression;

public class ExpressionVariableInteger extends ExpressionVariable<Integer> {

    public ExpressionVariableInteger(Integer value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Integer;
    }

    @Override
    public void setValue(String answer) {
        this.value = Integer.parseInt(answer);
    }

    // TODO
    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        double right = Double.parseDouble(other.value.toString());
        if (right == 0.0) {
            return new ExpressionVariableUndefined();
        }
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) / right));
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) * Double.parseDouble(other.value.toString())));
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) - Double.parseDouble(other.value.toString())));
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) + Double.parseDouble(other.value.toString())));
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) >= Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) > Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) <= Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) < Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable not() {
        return new ExpressionVariableUndefined();
    }
}
