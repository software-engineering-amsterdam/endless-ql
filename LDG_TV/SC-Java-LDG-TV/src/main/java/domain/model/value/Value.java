package domain.model.value;

import io.reactivex.functions.Consumer;

public abstract class Value<T> implements Consumer<T> {

    public abstract T getValue(); // TODO find better name

    public abstract void setValue(T value);
}
