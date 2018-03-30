package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class QLDataTypeWrapper<T> {

    T value;

    public abstract Expression.DataType getType();

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public static QLDataTypeWrapper createValue(Expression.DataType dataType, String stringValue) {
        switch (dataType) {
            case BOOLEAN:
                return new QLBoolean(stringValue.toUpperCase().equals("TRUE"));
            case STRING:
                return new QLString(stringValue);
            case INTEGER:
                if (stringValue.isEmpty())
                    return new QLInteger(BigInteger.valueOf(0));
                else
                    return new QLInteger(new BigInteger(stringValue));
            case DECIMAL:
                if (stringValue.isEmpty())
                    return new QLDecimal(new BigDecimal(0));
                else
                    return new QLDecimal(new BigDecimal(stringValue));
            case MONEY:
                if (stringValue.isEmpty())
                    return new QLMoney(new BigDecimal(0));
                else
                    return new QLMoney(new BigDecimal(stringValue));
            case DATE:
                if (stringValue.isEmpty()) {
                    return new QLDate(LocalDate.now());
                } else {
                    LocalDate date = LocalDate.now();
                    try {
                        date = LocalDate.parse(stringValue);
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("Unable to parse the date.");
                    }
                    return new QLDate(date);
                }

        }
        return null;
    }

    QLDataTypeWrapper(T value) {
        this.value = value;
    }

    public QLBoolean equals(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                this.value.equals(rhs.getValue())
                        && this.getType() == rhs.getType()
        );
    }

    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                !this.value.equals(rhs.getValue())
                        || this.getType() != rhs.getType()
        );
    }

    public QLDataTypeWrapper add(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLDataTypeWrapper negate() {
        throw new RuntimeException("Illegal operation");
    }

    public QLDataTypeWrapper subtract(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLDataTypeWrapper multiply(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLDataTypeWrapper divide(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLBoolean greaterThan(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLBoolean greaterEqual(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLBoolean lessThan(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLBoolean lessEqual(QLDataTypeWrapper rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLBoolean and(QLBoolean rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public QLBoolean or(QLBoolean rhs) {
        throw new RuntimeException("Illegal operation");
    }

    public static BigDecimal castToBigDecimal(Object value) {
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        } else if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        throw new RuntimeException("Unable to cast " + value.getClass().getSimpleName() + " to " + BigDecimal.class.getSimpleName());
    }
}
