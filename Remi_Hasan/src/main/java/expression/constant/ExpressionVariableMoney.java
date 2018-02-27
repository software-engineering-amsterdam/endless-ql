package expression.constant;

import expression.ReturnType;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ExpressionVariableMoney extends ExpressionVariable<BigDecimal> {

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
            this.value = BigDecimal.valueOf(Double.parseDouble(value));
    }

    public ExpressionVariableMoney(BigDecimal value) {
        super(value);
    }

    @Override
    public String toString() {
        // Print money value with 2 decimals, even if they are zero
        // Example: 5.00 and 4.95
        return new DecimalFormat("#0.##").format(this);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.MONEY;
    }

    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableMoney(this.value.divide(otherBigDecimal));
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableMoney(this.value.multiply(otherBigDecimal));
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableMoney(this.value.subtract(otherBigDecimal));
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableMoney(this.value.add(otherBigDecimal));
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) >= 0);
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) > 0);
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) <= 0);
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        BigDecimal otherBigDecimal = BigDecimal.valueOf(Double.parseDouble(other.evaluate().getValue().toString()));
        return new ExpressionVariableBoolean(this.value.compareTo(otherBigDecimal) < 0);
    }
}