package expression.variable;

import expression.ReturnType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpressionVariableNumber extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableNumber(BigDecimal value) {
        super(value);
    }

    public ExpressionVariableNumber(int value) {
        super(BigDecimal.valueOf(Double.valueOf(value)));
    }

    public ExpressionVariableNumber(double value) {
        super(BigDecimal.valueOf(value));
    }

    @Override
    public void setValue(String value) {
        if (value.isEmpty())
            this.value = null;
        else
            this.value = BigDecimal.valueOf(Double.parseDouble(value));
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }

    public int getIntValue() {
        return this.value.intValue();
    }

    public double getDecimalValue() {
        return this.value.doubleValue();
    }

    public BigDecimal getMoneyValue() {
        return value.setScale(2, RoundingMode.CEILING);
    }

    public ReturnType getReturnType() {
        return ReturnType.NUMBER;
    }

    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        if (other.getReturnType() != ReturnType.NUMBER)
            throw new UnsupportedOperationException("Cannot divide with non-number.");

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        if (otherNumber.value.doubleValue() == 0.0)
            throw new ArithmeticException("Division by zero.");

        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        return new ExpressionVariableNumber(this.value.divide(otherNumber.value));
    }

    public ExpressionVariable multiply(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableNumber(this.value.multiply(otherNumber.value));
    }

    public ExpressionVariable subtract(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableNumber(this.value.subtract(otherNumber.value));
    }

    public ExpressionVariable sum(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableNumber(this.value.add(otherNumber.value));
    }

    public ExpressionVariable ge(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableBoolean(this.value.compareTo(otherNumber.value) >= 0);
    }

    public ExpressionVariable gt(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableBoolean(this.value.compareTo(otherNumber.value) > 0);
    }

    public ExpressionVariable le(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableBoolean(this.value.compareTo(otherNumber.value) <= 0);
    }

    public ExpressionVariable lt(ExpressionVariable other) {
        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        return new ExpressionVariableBoolean(this.value.compareTo(otherNumber.value) < 0);
    }
}
