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

    public Money(final Token token, final String currency, final String amount) {
        super(token);
        this.currency = currency;
        this.amount = new BigDecimal(amount);
    }

    public Money(final Token token, final MoneyType currency, final String amount) {
        super(token);
        this.currency = currency.toString();
        this.amount = new BigDecimal(amount);
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final BigDecimal getAmount() {
        return this.amount;
    }

    public final Type getType() {
        if (this.currency.equals("€"))
            return new Type(NodeType.MONEY_EURO);
        else if (this.currency.equals("$"))
            return new Type(NodeType.MONEY_DOLLAR);

        throw new NotImplementedException();
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
