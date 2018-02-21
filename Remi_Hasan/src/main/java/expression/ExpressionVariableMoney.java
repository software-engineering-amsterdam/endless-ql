package expression;

import java.math.BigDecimal;

public class ExpressionVariableMoney extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableMoney(BigDecimal value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Money;
    }

    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableMoney(this.value.divide(otherBigDecimal));
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableMoney(this.value.multiply(otherBigDecimal));
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableMoney(this.value.subtract(otherBigDecimal));
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableMoney(this.value.add(otherBigDecimal));
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) >= 0);
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) > 0);
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) <= 0);
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().get().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) < 0);
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
