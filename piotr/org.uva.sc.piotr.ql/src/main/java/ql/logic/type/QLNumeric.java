package ql.logic.type;

abstract public class QLNumeric<T> extends QLSummable<T> {
    public QLNumeric(T value) {
        super(value);
    }

    abstract public QLDecimal castToDecimal();

    abstract public QLNumeric negate();

    abstract public QLNumeric subtract(QLNumeric rhs);

    abstract public QLNumeric multiply(QLNumeric rhs);

    abstract public QLNumeric divide(QLNumeric rhs);

    abstract public QLBoolean greaterThan(QLNumeric rhs);

    abstract public QLBoolean greaterEqual(QLNumeric rhs);

    abstract public QLBoolean lessThan(QLNumeric rhs);

    abstract public QLBoolean lessEqual(QLNumeric rhs);
}
