package ql.logic.type;

abstract public class QLDataTypeSummable<T> extends QLDataType<T> {
    public QLDataTypeSummable(T value) {
        super(value);
    }

    abstract public QLDataTypeSummable add(QLDataTypeSummable rhs);
}
