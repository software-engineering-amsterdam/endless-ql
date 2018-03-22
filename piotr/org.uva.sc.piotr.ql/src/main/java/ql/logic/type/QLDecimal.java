package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QLDecimal extends QLNumeric<BigDecimal> {

    public QLDecimal(BigDecimal value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.DECIMAL;
    }

    @Override
    public QLDecimal castToDecimal() {
        return this;
    }

    @Override
    public QLDecimal negate() {
        return new QLDecimal(this.value.negate());
    }

    // binary operations
    @Override
    public QLDecimal add(QLSummable rhs) {
        return new QLDecimal(this.value.add(castValueFromQLDataTypeNumeric(rhs.getValue())));
    }

    @Override
    public QLDecimal subtract(QLNumeric rhs) {
        return new QLDecimal(this.value.subtract(rhs.castToDecimal().getValue()));
    }

    @Override
    public QLDecimal multiply(QLNumeric rhs) {
        return new QLDecimal(this.value.multiply(rhs.castToDecimal().getValue()));
    }

    @Override
    public QLDecimal divide(QLNumeric rhs) {
        BigDecimal rhsValue = rhs.castToDecimal().getValue();
        if (rhsValue.compareTo(BigDecimal.ZERO) != 0) {
            return new QLDecimal(this.value.divide(rhs.castToDecimal().getValue(), 8, BigDecimal.ROUND_DOWN));
        } else if (this.value.compareTo(BigDecimal.ZERO) >= 0) {
            // plus infinity
            return new QLDecimal(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN));
        } else {
            // minus infinity
            return new QLDecimal(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).negate());
        }
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castValueFromQLDataTypeNumeric(rhs.getValue())) == 0);
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castValueFromQLDataTypeNumeric(rhs.getValue())) != 0);
    }

    @Override
    public QLBoolean greaterThan(QLNumeric rhs) {
        return new QLBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) > 0);
    }

    @Override
    public QLBoolean greaterEqual(QLNumeric rhs) {
        return new QLBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) >= 0);
    }

    @Override
    public QLBoolean lessThan(QLNumeric rhs) {
        return new QLBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) < 0);
    }

    @Override
    public QLBoolean lessEqual(QLNumeric rhs) {
        return new QLBoolean(this.value.compareTo(rhs.castToDecimal().getValue()) <= 0);
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
