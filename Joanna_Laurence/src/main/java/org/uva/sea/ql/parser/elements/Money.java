package org.uva.sea.ql.parser.elements;

public class Money extends Expr {
    private String currency;
    private Double cents;

    public Money(String currency, Double cents) {
        this.currency = currency;
        this.cents = cents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCents() {
        return cents;
    }

    public void setCents(Double cents) {
        this.cents = cents;
    }
}
