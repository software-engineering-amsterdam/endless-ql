package expression.variable;

import analysis.SymbolTable;
import expression.ReturnType;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExpressionVariableNumber extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableNumber(BigDecimal value) {
        super(value);
    }

    public ExpressionVariableNumber(String value) {
        if(!value.isEmpty()) {
            this.value = new BigDecimal(value);
        }
    }

    public ExpressionVariableNumber(int value) {
        super(new BigDecimal(value));
    }

    public ExpressionVariableNumber(double value) {
        super(new BigDecimal(value));
    }

    @Override
    public void setValue(String value) {
        if (value == null || value.isEmpty())
            this.value = null;
        else
            this.value = new BigDecimal(value);
    }

    @Override
    public Integer getIntValue() {
        return this.value.intValue();
    }

    @Override
    public Double getDecimalValue() {
        return this.value.doubleValue();
    }

    @Override
    public BigDecimal getMoneyValue() {
        return this.value.setScale(2, RoundingMode.CEILING);
    }

    public ReturnType getReturnType(SymbolTable symbolTable) {
        return ReturnType.NUMBER;
    }

    @Override
    public ExpressionVariable divide(ExpressionVariable other) {
        ExpressionVariableNumber otherNumber = (ExpressionVariableNumber) other;
        if (otherNumber.value.doubleValue() == 0.0)
            throw new ArithmeticException("Division by zero.");

        if(this.value == null || other.value == null)
            return new ExpressionVariableUndefined();

        return new ExpressionVariableNumber(this.value.divide(otherNumber.value, MathContext.DECIMAL128));
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
