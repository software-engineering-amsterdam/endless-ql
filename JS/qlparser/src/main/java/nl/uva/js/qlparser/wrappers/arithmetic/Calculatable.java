package nl.uva.js.qlparser.wrappers.arithmetic;

public abstract class Calculatable<T> {
    private T storage;

    Calculatable(T value) {
        storage = value;
    }

    T get() {
        return storage;
    }

    public abstract Calculatable<T> plus(Calculatable<T> other);
    public abstract Calculatable<T> min(Calculatable<T> other);
    public abstract Calculatable<T> mult(Calculatable<T> other);
    public abstract Calculatable<T> div(Calculatable<T> other);

    @Override
    public String toString() {
        return get().toString();
    }
}
