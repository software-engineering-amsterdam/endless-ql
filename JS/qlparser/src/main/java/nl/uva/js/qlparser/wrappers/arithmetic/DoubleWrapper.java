package nl.uva.js.qlparser.wrappers.arithmetic;

public class DoubleWrapper extends NumberWrapper<Double> {
    private Double storage;

    private DoubleWrapper(Double value) {
        storage = value;
    }

    public DoubleWrapper(String value) {
        storage = Double.valueOf(value);
    }

    @Override
    public Double get() {
        return storage;
    }

    @Override
    public NumberWrapper<Double> plus(NumberWrapper<Double> other) {
        return new DoubleWrapper(storage + other.get());
    }

    @Override
    public NumberWrapper<Double> min(NumberWrapper<Double> other) {
        return new DoubleWrapper(storage - other.get());
    }

    @Override
    public NumberWrapper<Double> mult(NumberWrapper<Double> other) {
        return new DoubleWrapper(storage * other.get());
    }

    @Override
    public NumberWrapper<Double> div(NumberWrapper<Double> other) {
        return new DoubleWrapper(storage / other.get());
    }
}
