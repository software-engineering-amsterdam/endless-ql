package nl.uva.js.qlparser.wrappers.arithmetic;

import org.joda.money.Money;

import java.math.RoundingMode;

public class CalculatableMoney extends Calculatable<Money> {
    private CalculatableMoney(Money value) {
        super(value);
    }

    public CalculatableMoney(String value) {
        super(Money.parse(value));
    }

    @Override
    public Calculatable<Money> plus(Calculatable<Money> other) {
        return new CalculatableMoney(this.get().plus(other.get()));
    }

    @Override
    public Calculatable<Money> min(Calculatable<Money> other) {
        return new CalculatableMoney(this.get().minus(other.get()));
    }

    @Override
    public Calculatable<Money> mult(Calculatable<Money> other) {
        return new CalculatableMoney(this.get().multipliedBy(other.get().getAmount(), RoundingMode.HALF_UP));
    }

    @Override
    public Calculatable<Money> div(Calculatable<Money> other) {
        return new CalculatableMoney(this.get().dividedBy(other.get().getAmount(), RoundingMode.HALF_UP));
    }
}
