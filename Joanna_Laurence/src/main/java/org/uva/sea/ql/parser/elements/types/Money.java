package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.Traverse;

import java.math.BigInteger;

public class Money extends ASTNode {
    private String currency;
    private BigInteger amount;

    public Money(String currency, String amount) {
        this.currency = currency;
        this.amount = new BigInteger(amount);
    }

    public String getCurrency() {
        return currency;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doMoney(this);
    }

    public Type getType() {
        return new Type("money");
    }
}
