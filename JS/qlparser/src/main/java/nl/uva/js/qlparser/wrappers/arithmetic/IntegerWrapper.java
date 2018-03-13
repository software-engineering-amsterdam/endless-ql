package nl.uva.js.qlparser.wrappers.arithmetic;

public class IntegerWrapper extends NumberWrapper<Integer> {
    private Integer storage;

    private IntegerWrapper(Integer value) {
        storage = value;
    }

    public IntegerWrapper(String value) {
        storage = Integer.valueOf(value);
    }

    @Override
    public Integer get() {
        return storage;
    }

    @Override
    public NumberWrapper<Integer> plus(NumberWrapper<Integer> other) {
        return new IntegerWrapper(storage + other.get());
    }

    @Override
    public NumberWrapper<Integer> min(NumberWrapper<Integer> other) {
        return new IntegerWrapper(storage - other.get());
    }

    @Override
    public NumberWrapper<Integer> mult(NumberWrapper<Integer> other) {
        return new IntegerWrapper(storage * other.get());
    }

    @Override
    public NumberWrapper<Integer> div(NumberWrapper<Integer> other) {
        return new IntegerWrapper(storage / other.get());
    }
}
