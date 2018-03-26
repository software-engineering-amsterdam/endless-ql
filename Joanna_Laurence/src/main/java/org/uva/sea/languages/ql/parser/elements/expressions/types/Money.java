package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.MoneyType;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;

public class Money extends Expression {
    private final String currency;
    private final BigDecimal amount;

    public Money(Token token, String currency, String amount) {
        super(token);
        this.currency = currency;
        this.amount = new BigDecimal(amount);
    }

    public Money(Token token, MoneyType currency, String amount) {
        super(token);
        this.currency = currency.toString();
        this.amount = new BigDecimal(amount);
    }

    public String getCurrency() {
        return this.currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Type getType() {
        if (this.currency.equals("€"))
            return new Type(NodeType.MONEY_EURO);
        else if (this.currency.equals("$"))
            return new Type(NodeType.MONEY_DOLLAR);

        throw new NotImplementedException();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
