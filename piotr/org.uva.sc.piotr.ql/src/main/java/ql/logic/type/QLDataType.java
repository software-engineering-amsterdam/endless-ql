package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class QLDataType<T> {

    T value;

    public abstract Expression.DataType getType();

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public static QLDataType createValue(Expression.DataType dataType, String stringValue) {
        switch (dataType) {
            case BOOLEAN:
                return new QLDataTypeBoolean(stringValue.toUpperCase().equals("TRUE"));
            case STRING:
                return new QLDataTypeString(stringValue);
            case INTEGER:
                if (stringValue.isEmpty())
                    return new QLDataTypeInteger(BigInteger.valueOf(0));
                else
                    return new QLDataTypeInteger(new BigInteger(stringValue));
            case DECIMAL:
                if (stringValue.isEmpty())
                    return new QLDataTypeDecimal(new BigDecimal(0));
                else
                    return new QLDataTypeDecimal(new BigDecimal(stringValue));
        }
        return null;
    }

    public QLDataType(T value) {
        this.value = value;
    }

    public abstract QLDataTypeBoolean equals(QLDataType rhs);

    public abstract QLDataTypeBoolean notEquals(QLDataType rhs);

}
