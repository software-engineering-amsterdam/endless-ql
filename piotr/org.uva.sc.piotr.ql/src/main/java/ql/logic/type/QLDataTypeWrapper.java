package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

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
        }
        return null;
    }

    public QLDataTypeWrapper(T value) {
        this.value = value;
    }

    public abstract QLBoolean equals(QLDataTypeWrapper rhs);

    public abstract QLBoolean notEquals(QLDataTypeWrapper rhs);

}
