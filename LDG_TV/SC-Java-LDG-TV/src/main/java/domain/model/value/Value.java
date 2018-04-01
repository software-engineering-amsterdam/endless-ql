package domain.model.value;


public interface Value<T> {
    T getValue();

    T setValue(T o);
}
