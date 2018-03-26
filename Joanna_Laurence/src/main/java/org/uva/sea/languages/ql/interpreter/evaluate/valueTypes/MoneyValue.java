package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

public class MoneyValue extends Value {

    private final String currency;
    private final BigDecimal amount;

    public MoneyValue(final String value) throws InvalidParameterException {
        final String[] split = value.split(" ", 2);
        if (split.length != 2)
            throw new InvalidParameterException("Incorrect money type " + value);

        this.currency = split[0];
        this.amount = new BigDecimal(split[1]);
    }

    public MoneyValue(final String currency, final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public MoneyValue(final MoneyType moneyType, final BigDecimal amount) {
        this.currency = moneyType.toString();
        this.amount = amount;
    }

    private void validateCurrency(final MoneyValue value) throws EvaluationException {
        if (!this.currency.equals(value.currency))
            throw new EvaluationException("Currencies mismatch");
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public final Value add(final Value value) throws EvaluationException {
        return value.add(this);
    }

    @Override
    public final Value add(final IntValue value) {
        return new MoneyValue(this.currency, this.amount.add(new BigDecimal(value.getIntValue())));
    }

    @Override
    public final Value add(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new MoneyValue(this.currency, this.amount.add(value.amount));
    }

    @Override
    public final Value add(final DecimalValue value) {
        return new MoneyValue(this.currency, this.amount.add(BigDecimal.valueOf(value.getDecimalValue())));
    }

    @Override
    public final Value divide(final Value value) throws EvaluationException {
        return value.divide(this);
    }

    @Override
    public final Value divide(final IntValue value) throws EvaluationException {
        if (value.getIntValue() == 0)
            throw new EvaluationException("Divide by 0 displayError");

        return new MoneyValue(this.currency, this.amount.divide(new BigDecimal(value.getIntValue()), RoundingMode.UNNECESSARY));
    }

    @Override
    public final Value divide(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        if (value.amount.doubleValue() == 0.0)
            throw new EvaluationException("Divide by 0 displayError");

        return new DecimalValue(this.amount.divide(value.amount, RoundingMode.UNNECESSARY).doubleValue());
    }

    @Override
    public final Value divide(final DecimalValue value) throws EvaluationException {
        if (value.getDecimalValue() == 0)
            throw new EvaluationException("Divide by 0 displayError");

        return new MoneyValue(this.currency, this.amount.divide(BigDecimal.valueOf(value.getDecimalValue()), RoundingMode.UNNECESSARY));
    }

    @Override
    public final Value isEqual(final Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public final Value isEqual(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new BooleanValue(this.amount.compareTo(value.amount) == 0);
    }


    @Override
    public final Value isGreaterOrEqual(final Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public final Value isGreaterOrEqual(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new BooleanValue(this.amount.compareTo(value.amount) >= 0);
    }

    @Override
    public final Value isGreaterThan(final Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public final Value isGreaterThan(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new BooleanValue(this.amount.compareTo(value.amount) > 0);
    }

    @Override
    public final Value isLessOrEqual(final Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public final Value isLessOrEqual(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new BooleanValue(this.amount.compareTo(value.amount) <= 0);
    }

    @Override
    public final Value isLessThan(final Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public final Value isLessThan(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new BooleanValue(this.amount.compareTo(value.amount) < 0);
    }

    @Override
    public final Value multiply(final Value value) throws EvaluationException {
        return value.multiply(this);
    }

    @Override
    public final Value multiply(final IntValue value) {
        return new MoneyValue(this.currency, this.amount.multiply(new BigDecimal(value.getIntValue())));
    }

    @Override
    public final Value multiply(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new MoneyValue(this.currency, this.amount.multiply(value.amount));
    }

    @Override
    public final Value multiply(final DecimalValue value) {
        return new MoneyValue(this.currency, this.amount.multiply(BigDecimal.valueOf(value.getDecimalValue())));
    }

    @Override
    public final Value isNotEqual(final Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public final Value isNotEqual(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new BooleanValue(this.amount.compareTo(value.amount) != 0);
    }

    @Override
    public final Value subtract(final IntValue value) {
        return new MoneyValue(this.currency, this.amount.subtract(new BigDecimal(value.getIntValue())));
    }

    @Override
    public final Value subtract(final MoneyValue value) throws EvaluationException {
        this.validateCurrency(value);

        return new MoneyValue(this.currency, this.amount.subtract(value.amount));
    }

    @Override
    public final Value subtract(final DecimalValue value) {
        return new MoneyValue(this.currency, this.amount.subtract(BigDecimal.valueOf(value.getDecimalValue())));
    }

    @Override
    public final Value negate() {
        return new MoneyValue(this.currency, this.amount.negate());
    }

    @Override
    public final Value positive() {
        return new MoneyValue(this.currency, this.amount.abs());
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        if (this.currency.equals("€"))
            return NodeType.MONEY_EURO;
        else if (this.currency.equals("$"))
            return NodeType.MONEY_DOLLAR;

        throw new NotImplementedException();
    }

    @Override
    public final String toString() {
        return (this.amount != null) ? (this.currency + this.amount) : "No value";
    }

    public final MoneyValue clone() throws CloneNotSupportedException {
        return (MoneyValue) super.clone();
    }
}
