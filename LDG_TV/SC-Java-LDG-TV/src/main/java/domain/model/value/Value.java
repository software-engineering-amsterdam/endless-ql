package domain.model.value;


public abstract class Value<T> {
    public abstract T getValue();
    public abstract T setValue(Object o);
}
