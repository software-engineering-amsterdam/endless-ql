package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;

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
        return new MoneyValue(this.currency, this.amount.add(new BigDecimal(value.getIntValue())));
    }

    public Value add(DecimalValue value) {
        return new MoneyValue(this.currency, this.amount.add(new BigDecimal(value.getDecimalValue())));
    }

    public Value add(MoneyValue value) {
        return new MoneyValue(value.currency, this.amount.add(value.getAmount()));
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

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value negate() {
        return new MoneyValue(this.currency, this.amount.negate());
    }

    @Override
    public Value not() {
        return new ErrorValue("Not operator cannot be applied on a money value");
    }

    @Override
    public Value positive() {
        return new MoneyValue(this.currency, this.amount);
    }
}
