package wrappers;

public abstract class NumberWrapper<T extends Number> {
    abstract public T get();
    abstract public NumberWrapper<T> plus(NumberWrapper<T> other);
    abstract public NumberWrapper<T> min(NumberWrapper<T> other);
    abstract public NumberWrapper<T> mult(NumberWrapper<T> other);
    abstract public NumberWrapper<T> div(NumberWrapper<T> other);

    @Override
    public String toString() {
        return get().toString();
    }
}
