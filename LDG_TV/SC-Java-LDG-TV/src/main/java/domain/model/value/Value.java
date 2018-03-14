package domain.model.value;

public abstract class Value<T> {

    public abstract T getValue(); // TODO find better name

    public abstract void setValue(T value);
}
