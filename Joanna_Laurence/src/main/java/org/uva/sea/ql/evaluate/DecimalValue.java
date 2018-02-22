package org.uva.sea.ql.evaluate;

import java.math.BigDecimal;

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
}
