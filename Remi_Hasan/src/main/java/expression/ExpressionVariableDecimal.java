package expression;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    public ExpressionVariableDecimal(Double value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Decimal;
    }

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
            this.value = Double.parseDouble(value);
    }

    // TODO
    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();

        double right = Double.parseDouble(other.value.toString());
        if (right == 0.0) {
            return new ExpressionVariableUndefined();
        }
        return new ExpressionVariableDecimal(this.value / right);
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableDecimal(this.value * Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableDecimal(this.value - Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableDecimal(this.value + Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value >= Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value > Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value <= Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        if (this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value < Double.parseDouble(other.value.toString()));
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