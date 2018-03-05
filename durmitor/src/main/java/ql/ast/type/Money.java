package ql.ast.type;

import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToMoney;
import ql.helpers.Currency;
import ql.visitors.interfaces.TypeVisitor;

public class Money extends Type {

    private Currency currency;
    
    public Money() {
        this.currency = Currency.defaultCurrency;
    }
    
    public Money(Currency currency) {
        this.currency = currency;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "money";
    }

    @Override
    public boolean equals(Type t) {
        return t.isMoney();
    }

    @Override
    public boolean isMoney() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Literal<?> parse(Literal<?> value) {
        return value.accept(new ToMoney(currency));
    }
}
