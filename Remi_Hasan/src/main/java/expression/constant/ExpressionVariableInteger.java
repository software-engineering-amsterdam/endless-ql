package expression.constant;

import expression.ReturnType;

import java.math.BigDecimal;

public class ExpressionVariableInteger extends ExpressionVariable<Integer> {

    public ExpressionVariableInteger(Integer value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.INTEGER;
    }

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
            this.value = Integer.parseInt(value);
    }

    // TODO
    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        double right = Double.parseDouble(other.value.toString());
        if (right == 0.0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) / right));
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) * Double.parseDouble(other.value.toString())));
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableInteger((int) (Double.parseDouble(this.value.toString()) - Double.parseDouble(other.value.toString())));
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        switch(other.getReturnType()) {
            case DECIMAL:
                return new ExpressionVariableDecimal(this.value + (Double) other.value);
            case INTEGER:
                return new ExpressionVariableInteger(this.value + (Integer) other.value);
            case MONEY:
                return new ExpressionVariableMoney(((BigDecimal) other.value).add(new BigDecimal(this.value)));
            default:
                return new ExpressionVariableUndefined();
        }
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) >= Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) > Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) <= Double.parseDouble(other.value.toString()));
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(Double.parseDouble(this.value.toString()) < Double.parseDouble(other.value.toString()));
    }
}