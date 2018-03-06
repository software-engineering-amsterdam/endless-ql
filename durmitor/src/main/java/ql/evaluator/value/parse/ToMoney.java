package ql.evaluator.value.parse;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.helpers.Currency;
import ql.visitors.interfaces.ValueVisitor;

public class ToMoney implements ValueVisitor {

    private Currency currency;
    
    public ToMoney(Currency currency) {
        this.currency = currency;
    }
    
    @Override
    public Literal<?> visit(BoolLiteral value) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(StrLiteral value) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(IntLiteral value) {
        return new MoneyLiteral(value.getValue().doubleValue());
    }

    @Override
    public Literal<?> visit(DecimalLiteral value) {
        return new MoneyLiteral(value.getValue());
    }

    @Override
    public Literal<?> visit(MoneyLiteral value) {
        return (currency.equals(value.getCurrency()))? value : value.convertTo(currency);
    }

    @Override
    public Literal<?> visit(DateLiteral value) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(UndefinedLiteral value) {
        return new MoneyLiteral();
    }
}
