package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QLDataTypeDecimal extends QLDataTypeNumeric<BigDecimal> {

    public QLDataTypeDecimal(BigDecimal value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.DECIMAL;
    }

    @Override
    public QLDataTypeDecimal castToDecimal() {
        return this;
    }

    @Override
    public QLDataTypeDecimal negate() {
        return new QLDataTypeDecimal(this.value.negate());
    }

    // binary operations
    @Override
    public QLDataTypeDecimal add(QLDataTypeSummable rhs) {
        return new QLDataTypeDecimal(this.value.add(castValueFromQLDataTypeNumeric(rhs.getValue())));
    }

    @Override
    public QLDataTypeDecimal subtract(QLDataTypeNumeric rhs) {
        return new QLDataTypeDecimal(this.value.subtract(rhs.castToDecimal().getValue()));
    }

    @Override
    public QLDataTypeDecimal multiply(QLDataTypeNumeric rhs) {
        return new QLDataTypeDecimal(this.value.multiply(rhs.castToDecimal().getValue()));
    }

    @Override
    public QLDataTypeDecimal divide(QLDataTypeNumeric rhs) {
        BigDecimal rhsValue = rhs.castToDecimal().getValue();
        if (rhsValue.compareTo(BigDecimal.ZERO) != 0) {
            return new QLDataTypeDecimal(this.value.divide(rhs.castToDecimal().getValue(), 8, BigDecimal.ROUND_DOWN));
        } else if (this.value.compareTo(BigDecimal.ZERO) >= 0) {
            // plus infinity
            return new QLDataTypeDecimal(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN));
        } else {
            // minus infinity
            return new QLDataTypeDecimal(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).negate());
        }
    }

    @Override
    public QLDataTypeBoolean equals(QLDataType rhs) {
        return new QLDataTypeBoolean(this.value.compareTo(castValueFromQLDataTypeNumeric(rhs.getValue())) == 0);
    }

    @Override
    public QLDataTypeBoolean notEquals(QLDataType rhs) {
        return new QLDataTypeBoolean(this.value.compareTo(castValueFromQLDataTypeNumeric(rhs.getValue())) != 0);
    }

    @Override
    public QLDataTypeBoolean greaterThan(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) > 0);
    }

    @Override
    public QLDataTypeBoolean greaterEqual(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) >= 0);
    }

    @Override
    public QLDataTypeBoolean lessThan(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) < 0);
    }

    @Override
    public QLDataTypeBoolean lessEqual(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) <= 0);
    }

    private static BigDecimal castValueFromQLDataTypeNumeric(Object value) {
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        } else if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        return null;
    }
}
