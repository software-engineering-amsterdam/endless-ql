package org.uva.sea.ql.evaluate;

import java.math.BigDecimal;

public class MoneyValue extends Value {

    private String currency;
    private BigDecimal amount;

    public MoneyValue(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Value add(IntValue value) {
        return new MoneyValue(this.getCurrency(), this.getAmount().add(new BigDecimal(value.getIntValue())));
    }

    public Value add(DecimalValue value) {
        return new MoneyValue(this.getCurrency(), this.getAmount().add(new BigDecimal(value.getDecimalValue())));
    }

    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), this.getAmount().add(value.getAmount()));
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Cannot add boolean to money");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Cannot add date to money");
    }

    public Value add(StringValue value) {
        return new ErrorValue("Cannot add string to money");
    }
}
