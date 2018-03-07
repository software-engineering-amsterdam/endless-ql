package astvisitor;

import java.math.BigDecimal;
import java.math.MathContext;

public class NumValue extends Value<BigDecimal> {

    NumValue(BigDecimal value) {
        super(value);
    }
    NumValue(Integer value) {
        super(new BigDecimal(value.toString()));
    }
    NumValue(Double value) {
        super(new BigDecimal(value.toString()));
    }

    @Override
    public NumValue divide(Value right) {
        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.divide(rightValue.value, MathContext.DECIMAL128));
    }

    @Override
    public NumValue multiply(Value right) {
        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.multiply(rightValue.value));
    }

    @Override
    public NumValue subtract(Value right) {
        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.subtract(rightValue.value));
    }

    @Override
    public NumValue sum(Value right) {
        NumValue rightValue = (NumValue) right;
        return new NumValue(this.value.add(rightValue.value));
    }

    @Override
    public BoolValue eq(Value right) {
        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.equals(rightValue.value));
    }

    @Override
    public BoolValue ge(Value right) {
        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) >= 0);
    }

    @Override
    public BoolValue gt(Value right) {
        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) > 0);
    }

    @Override
    public BoolValue le(Value right) {
        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) <= 0);
    }

    @Override
    public BoolValue lt(Value right) {
        NumValue rightValue = (NumValue) right;
        return new BoolValue(this.value.compareTo(rightValue.value) < 0);
    }

    @Override
    public BoolValue and(Value right) {
        throw new UnsupportedOperationException("Cannot perform and on integer.");
    }

    @Override
    public BoolValue or(Value right) {
        throw new UnsupportedOperationException("Cannot perform or on integer.");
    }

    @Override
    public BoolValue not() {
        throw new UnsupportedOperationException("Cannot perform not on integer.");
    }

    @Override
    public NumValue neg() {
        return new NumValue(this.value.multiply(new BigDecimal(-1.0)));
    }
}
