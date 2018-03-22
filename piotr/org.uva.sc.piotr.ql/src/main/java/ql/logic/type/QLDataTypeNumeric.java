package ql.logic.type;

abstract public class QLDataTypeNumeric<T> extends QLDataTypeSummable<T> {
    public QLDataTypeNumeric(T value) {
        super(value);
    }

    abstract public QLDataTypeDecimal castToDecimal();

    abstract public QLDataTypeNumeric negate();

    abstract public QLDataTypeNumeric subtract(QLDataTypeNumeric rhs);

    abstract public QLDataTypeNumeric multiply(QLDataTypeNumeric rhs);

    abstract public QLDataTypeNumeric divide(QLDataTypeNumeric rhs);

    abstract public QLDataTypeBoolean greaterThan(QLDataTypeNumeric rhs);

    abstract public QLDataTypeBoolean greaterEqual(QLDataTypeNumeric rhs);

    abstract public QLDataTypeBoolean lessThan(QLDataTypeNumeric rhs);

    abstract public QLDataTypeBoolean lessEqual(QLDataTypeNumeric rhs);
}
