package astvisitor;

import java.math.BigDecimal;

public class BoolValue extends Value<Boolean>{
    public BoolValue(Boolean value){
        super(value);
    }

    @Override
    public Boolean getBooleanValue() {
        return this.value;
    }

    @Override
    public Integer getIntValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to integer");
    }

    @Override
    public Double getDecimalValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to decimal");
    }

    @Override
    public BigDecimal getMoneyValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to money");
    }

    @Override
    public String getStringValue() {
        throw new UnsupportedOperationException("Cannot cast boolean to string");
    }

    @Override
    public NumValue divide(Value right) {
        throw new UnsupportedOperationException("Cannot perform divide on boolean.");
    }

    @Override
    public NumValue multiply(Value right) {
        throw new UnsupportedOperationException("Cannot perform multiply on boolean.");
    }

    @Override
    public NumValue subtract(Value right) {
        throw new UnsupportedOperationException("Cannot perform subtract on boolean.");
    }

    @Override
    public NumValue sum(Value right) {
        throw new UnsupportedOperationException("Cannot perform sum on boolean.");
    }

    @Override
    public BoolValue eq(Value right) {
        return new BoolValue(this.value.equals(right.value));
    }

    @Override
    public BoolValue ge(Value right) {
        throw new UnsupportedOperationException("Cannot perform ge on boolean.");
    }

    @Override
    public BoolValue gt(Value right) {
        throw new UnsupportedOperationException("Cannot perform gt on boolean.");
    }

    @Override
    public BoolValue le(Value right) {
        throw new UnsupportedOperationException("Cannot perform le on boolean.");
    }

    @Override
    public BoolValue lt(Value right) {
        throw new UnsupportedOperationException("Cannot perform lt on boolean.");
    }

    @Override
    public BoolValue and(Value right) {
        return new BoolValue(this.getBooleanValue() && right.getBooleanValue());
    }

    @Override
    public BoolValue or(Value right) {
        return new BoolValue(this.getBooleanValue() || right.getBooleanValue());
    }

    @Override
    public BoolValue not() {
        return new BoolValue(!this.getBooleanValue());
    }

    @Override
    public NumValue neg() {
        throw new UnsupportedOperationException("Cannot perform neg on boolean.");
    }
}
