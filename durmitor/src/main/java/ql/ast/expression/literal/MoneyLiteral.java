package ql.ast.expression.literal;

import ql.ast.type.Money;
import ql.ast.type.Type;
import ql.evaluator.arithmetic.add.MoneyAdd;
import ql.evaluator.arithmetic.divide.MoneyDivide;
import ql.evaluator.arithmetic.multiply.MoneyMultiply;
import ql.evaluator.arithmetic.subtract.MoneySubtract;
import ql.evaluator.comparisons.equal.MoneyEqual;
import ql.evaluator.comparisons.greater.MoneyGreater;
import ql.evaluator.comparisons.greaterequal.MoneyGreaterEqual;
import ql.evaluator.comparisons.less.MoneyLess;
import ql.evaluator.comparisons.lessequal.MoneyLessEqual;
import ql.evaluator.comparisons.notequal.MoneyNotEqual;
import ql.helpers.Currency;
import ql.helpers.Exchange;
import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class MoneyLiteral extends NumberLiteral implements ExpressionVisitable {

    private Currency currency = Currency.defaultCurrency;
    private Double value;

    public MoneyLiteral() {
        setValue("");
    }

    public MoneyLiteral(String value) {
        setValue(value);
    }
    
    public MoneyLiteral(Currency currency, String value) {
        setCurrency(currency);
        setValue(value);
    }

    public MoneyLiteral(Number value) {
        setValue(value);
    }
    
    public MoneyLiteral(Currency currency, Number value) {
        setCurrency(currency);
        setValue(value);
    }
    
    private void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    private void setValue(String value) {
        this.value = (value == null || value.isEmpty())? 0.0 : Double.valueOf(value);
    }

    @Override
    public String toString() {
        return String.format("%s %."+currency.getDecimalPlaces()+"f", currency,value);
    }
    
    @Override
    public Double getValue() {
        return value;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    public void setValue(Number value) {
        this.value = value.doubleValue();
    }

    @Override
    public Type getType() {
        return new Money(currency);
    }

    @Override
    public Literal<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Literal<?> negative() {
        return new MoneyLiteral(currency, value * -1);
    }

    @Override
    public Literal<?> positive() {
        return this;
    }

    @Override
    public Literal<?> add(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyAdd(this));
    }

    @Override
    public Literal<?> subtract(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneySubtract(this));
    }

    @Override
    public Literal<?> multiply(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyMultiply(this));
    }

    @Override
    public Literal<?> divide(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyDivide(this));
    }

    @Override
    public Literal<?> less(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyLess(this));
    }

    @Override
    public Literal<?> lessEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyLessEqual(this));
    }

    @Override
    public Literal<?> greater(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyGreater(this));
    }

    @Override
    public Literal<?> greaterEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyGreaterEqual(this));
    }

    @Override
    public Literal<?> equal(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyEqual(this));
    }

    @Override
    public Literal<?> notEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new MoneyNotEqual(this));
    }

    public Literal<?> convertTo(Currency target) {
        
        if(Exchange.hasRateFor(currency,target))
        {
            return new MoneyLiteral(target, value * Exchange.getExchangeRate(currency,target));
        }
        return new UndefinedLiteral();
    }
}
