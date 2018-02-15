package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Traverse;

public class Money extends ASTNode {
    private String currency;
    private Double amount;

    public Money(String currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void traverse(Traverse traverse) {
        traverse.doMoney(this);
    }

    public Type getType() {
        return new Type("money");
    }
}
