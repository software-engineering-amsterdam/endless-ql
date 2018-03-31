package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QLInteger extends QLDataTypeWrapper<BigInteger> {

    public QLInteger(BigInteger value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.INTEGER;
    }

    @Override
    public QLInteger negate() {
        return new QLInteger(this.value.negate());
    }

    @Override
    public QLDataTypeWrapper add(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).add(rhs);
        if (rhs instanceof QLInteger)
            return new QLInteger(this.value.add((BigInteger) rhs.getValue()));
        return super.add(rhs);
    }

    @Override
    public QLDataTypeWrapper subtract(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).subtract(rhs);
        if (rhs instanceof QLInteger)
            return new QLInteger(this.value.subtract((BigInteger) rhs.getValue()));
        return super.subtract(rhs);
    }

    @Override
    public QLDataTypeWrapper multiply(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).multiply(rhs);
        if (rhs instanceof QLInteger)
            return new QLInteger(this.value.multiply((BigInteger) rhs.getValue()));
        if (rhs instanceof QLMoney)
            // integer * money -> money
            return new QLMoney(QLDecimal.castToBigDecimal(this.value).multiply(castToBigDecimal(rhs.getValue())));
        return super.multiply(rhs);
    }

    @Override
    public QLDataTypeWrapper divide(QLDataTypeWrapper rhs) {

        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).divide(rhs);

        if (rhs instanceof QLInteger) {
            BigInteger rhsValue = (BigInteger) rhs.getValue();
            if (rhsValue.compareTo(BigInteger.ZERO) != 0) {
                return new QLInteger(this.value.divide(rhsValue));
            } else if (this.value.compareTo(BigInteger.ZERO) >= 0) {
                // plus infinity
                return new QLInteger(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).toBigIntegerExact());
            } else {
                // minus infinity
                return new QLInteger(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).negate().toBigIntegerExact());
            }
        }
        return super.divide(rhs);
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).equals(rhs);
        if (rhs instanceof QLInteger)
            return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) == 0);
        return super.equals(rhs);
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).notEquals(rhs);
        if (rhs instanceof QLInteger)
            return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) != 0);
        return super.notEquals(rhs);
    }

    @Override
    public QLBoolean greaterThan(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).greaterThan(rhs);
        if (rhs instanceof QLInteger)
            return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) > 0);
        return super.greaterThan(rhs);
    }

    @Override
    public QLBoolean greaterEqual(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).greaterEqual(rhs);
        if (rhs instanceof QLInteger)
            return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) >= 0);
        return super.greaterEqual(rhs);
    }

    @Override
    public QLBoolean lessThan(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).lessThan(rhs);
        if (rhs instanceof QLInteger)
            return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) < 0);
        return super.lessThan(rhs);
    }

    @Override
    public QLBoolean lessEqual(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal)
            return (new QLDecimal(new BigDecimal(this.value))).lessEqual(rhs);
        if (rhs instanceof QLInteger)
            return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) <= 0);
        return super.lessEqual(rhs);
    }
}
