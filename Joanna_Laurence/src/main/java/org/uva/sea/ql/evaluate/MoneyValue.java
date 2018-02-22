package org.uva.sea.ql.evaluate;

import java.math.BigDecimal;

public class MoneyValue extends Value{

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
}
