package astvisitor;

public abstract class Value<T> {
    public final T value;
    Value(T value){
        this.value = value;
    }

    public abstract NumValue divide(Value right);
}


