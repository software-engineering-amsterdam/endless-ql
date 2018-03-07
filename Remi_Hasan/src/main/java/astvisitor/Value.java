package astvisitor;

public abstract class Value<T> {
    public final T value;
    Value(T value){
        this.value = value;
    }

    public abstract NumValue divide(Value right);
    public abstract NumValue multiply(Value right);
    public abstract NumValue subtract(Value right);
    public abstract NumValue sum(Value right);

    public abstract BoolValue eq(Value right);

    public abstract BoolValue ge(Value right);
    public abstract BoolValue gt(Value right);
    public abstract BoolValue le(Value right);
    public abstract BoolValue lt(Value right);
    public abstract BoolValue and(Value right);
    public abstract BoolValue or(Value right);

    public abstract BoolValue not();
    public abstract NumValue neg();

    @Override
    public abstract boolean equals(Object other);
}


