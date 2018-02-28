package org.uva.sea.ql.parser.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.visitor.Visitor;

import java.math.BigDecimal;

public class Money extends ASTNode  {
    private String currency;
    private BigDecimal amount;

    public Money(Token token, String currency, String amount) {
        super(token);
        this.currency = currency;
        this.amount = new BigDecimal(amount);
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Type getType() {
        return new Type(NodeType.MONEY);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
