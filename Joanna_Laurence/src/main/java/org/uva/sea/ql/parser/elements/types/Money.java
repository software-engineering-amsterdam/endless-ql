package org.uva.sea.ql.parser.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.visitor.IASTVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        if(this.currency.equals("€"))
            return new Type(NodeType.MONEY_EURO);
        else if(this.currency.equals("$"))
            return new Type(NodeType.MONEY_DOLLAR);

        throw new NotImplementedException();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
