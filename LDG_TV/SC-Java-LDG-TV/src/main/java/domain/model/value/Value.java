package domain.model.value;


public interface Value<T> {
    public T getValue();
    public T setValue(Object o);
}
