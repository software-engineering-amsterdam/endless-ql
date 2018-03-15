package nl.uva.js.qlparser.wrappers.arithmetic;

public class CalculatableDouble extends Calculatable<Double> {
    private CalculatableDouble(Double value) {
        super(value);
    }

    public CalculatableDouble(String value) {
        super(Double.valueOf(value));
    }

    @Override
    public Calculatable<Double> plus(Calculatable<Double> other) {
        return new CalculatableDouble(this.get() + other.get());
    }

    @Override
    public Calculatable<Double> min(Calculatable<Double> other) {
        return new CalculatableDouble(this.get() - other.get());
    }

    @Override
    public Calculatable<Double> mult(Calculatable<Double> other) {
        return new CalculatableDouble(this.get() * other.get());
    }

    @Override
    public Calculatable<Double> div(Calculatable<Double> other) {
        return new CalculatableDouble(this.get() / other.get());
    }
}
