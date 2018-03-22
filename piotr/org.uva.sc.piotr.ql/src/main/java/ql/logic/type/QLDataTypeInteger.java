package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QLDataTypeInteger extends QLDataTypeNumeric<BigInteger> {

    public QLDataTypeInteger(BigInteger value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.INTEGER;
    }

    @Override
    public QLDataTypeDecimal castToDecimal() {
        return new QLDataTypeDecimal(new BigDecimal(this.value));
    }

    @Override
    public QLDataTypeInteger negate() {
        return new QLDataTypeInteger(this.value.negate());
    }

    @Override
    public QLDataTypeInteger add(QLDataTypeSummable rhs) {
        return new QLDataTypeInteger(this.value.add((BigInteger) rhs.getValue()));
    }

    @Override
    public QLDataTypeInteger subtract(QLDataTypeNumeric rhs) {
        return new QLDataTypeInteger(this.value.subtract((BigInteger) rhs.getValue()));
    }

    @Override
    public QLDataTypeInteger multiply(QLDataTypeNumeric rhs) {
        return new QLDataTypeInteger(this.value.multiply((BigInteger) rhs.getValue()));
    }

    @Override
    public QLDataTypeInteger divide(QLDataTypeNumeric rhs) {
        BigInteger rhsValue = (BigInteger) rhs.getValue();
        if (rhsValue.compareTo(BigInteger.ZERO) != 0) {
            return new QLDataTypeInteger(this.value.divide(rhsValue));
        } else if (this.value.compareTo(BigInteger.ZERO) >= 0) {
            // plus infinity
            return new QLDataTypeInteger(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).toBigIntegerExact());
        } else {
            // minus infinity
            return new QLDataTypeInteger(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).negate().toBigIntegerExact());
        }
    }

    @Override
    public QLDataTypeBoolean equals(QLDataType rhs) {
        return new QLDataTypeBoolean(this.value.compareTo((BigInteger) rhs.value) == 0);
    }

    @Override
    public QLDataTypeBoolean notEquals(QLDataType rhs) {
        return new QLDataTypeBoolean(this.value.compareTo((BigInteger) rhs.value) != 0);
    }

    @Override
    public QLDataTypeBoolean greaterThan(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo((BigInteger) rhs.value) > 0);
    }

    @Override
    public QLDataTypeBoolean greaterEqual(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo((BigInteger) rhs.value) >= 0);
    }

    @Override
    public QLDataTypeBoolean lessThan(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo((BigInteger) rhs.value) < 0);
    }

    @Override
    public QLDataTypeBoolean lessEqual(QLDataTypeNumeric rhs) {
        return new QLDataTypeBoolean(this.value.compareTo((BigInteger) rhs.value) <= 0);
    }
}
