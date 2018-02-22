package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitor;

import java.math.BigDecimal;

public class Money extends ASTNode  {
    private String currency;
    private BigDecimal amount;

    public Money(String currency, String amount) {
        this.currency = currency;
        this.amount = new BigDecimal(amount);
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("money");
    }
}
