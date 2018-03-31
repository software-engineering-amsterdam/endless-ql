package QL.Evaluation;

public abstract class Value<T> {
    private final T value;

    public Value(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Boolean isArithmetic(){ return false; }
    public Boolean isLogical(){ return false; }
}
