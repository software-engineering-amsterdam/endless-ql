package org.uva.sea.ql.evaluate;

import java.math.BigDecimal;
import org.uva.sea.ql.QLValueEvaluator;


public class DecimalValue extends Value {

    private double decimalValue;

    public DecimalValue(double decimalValue) {

        this.decimalValue = decimalValue;
    }

    public double getDecimalValue() {
        return decimalValue;
    }

    public Value add(IntValue value) {
        return new DecimalValue(this.decimalValue + value.getIntValue());
    }

    public Value add(DecimalValue value) {
        return new DecimalValue(this.decimalValue + value.getDecimalValue());
    }

    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), value.getAmount().add(new BigDecimal(this.decimalValue)));
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Cannot add boolean to decimal");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Cannot add date to decimal");
    }

    public Value add(StringValue value) {
        return new ErrorValue("Cannot add string to decimal");
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value negate() {
        return new DecimalValue(this.decimalValue * (-1));
    }

    @Override
    public Value not() {
        return new ErrorValue("Not operator cannot be applied on a decimal value");
    }

    @Override
    public Value positive() {
        return new DecimalValue(this.decimalValue);
    }
}
