package nl.uva.js.qlparser.wrappers.arithmetic;

public abstract class NumberWrapper<T extends Number> {
    protected abstract T get();
    public abstract NumberWrapper<T> plus(NumberWrapper<T> other);
    public abstract NumberWrapper<T> min(NumberWrapper<T> other);
    public abstract NumberWrapper<T> mult(NumberWrapper<T> other);
    public abstract NumberWrapper<T> div(NumberWrapper<T> other);

    @Override
    public String toString() {
        return get().toString();
    }
}
