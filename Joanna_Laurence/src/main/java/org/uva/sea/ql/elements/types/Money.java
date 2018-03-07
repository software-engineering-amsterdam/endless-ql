package org.uva.sea.ql.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.elements.ASTNode;
import org.uva.sea.ql.NodeType;
import org.uva.sea.visitor.IASTVisitor;

import java.math.BigDecimal;

public class Money extends ASTNode {
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
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
