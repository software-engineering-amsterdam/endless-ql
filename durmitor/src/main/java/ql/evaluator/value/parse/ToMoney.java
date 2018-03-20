package ql.evaluator.value.parse;

import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Money;
import ql.helpers.Currency;

public class ToMoney extends Convert {

    private Currency currency;
    
    public ToMoney(Currency currency) {
    		target			= new Money();
        this.currency	= currency;
    }
    
    @Override
    public MoneyLiteral visit(IntLiteral value) {
        return new MoneyLiteral(value.getValue().doubleValue());
    }

    @Override
    public MoneyLiteral visit(DecimalLiteral value) {
        return new MoneyLiteral(value.getValue());
    }

    @Override
    public MoneyLiteral visit(MoneyLiteral value) {
        return (currency.equals(value.getCurrency()))? value : value.convertTo(currency);
    }

    @Override
    public MoneyLiteral visit(UndefinedLiteral value) {
        return new MoneyLiteral();
    }
}
