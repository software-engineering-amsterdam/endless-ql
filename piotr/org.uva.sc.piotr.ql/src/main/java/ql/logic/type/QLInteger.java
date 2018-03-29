package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QLInteger extends QLNumeric<BigInteger> {

    public QLInteger(BigInteger value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.INTEGER;
    }

    @Override
    public QLDecimal castToDecimal() {
        return new QLDecimal(new BigDecimal(this.value));
    }

    @Override
    public QLInteger negate() {
        return new QLInteger(this.value.negate());
    }

    @Override
    public QLNumeric add(QLSummable rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).add(rhs);
        }
        return new QLInteger(this.value.add((BigInteger) rhs.getValue()));
    }

    @Override
    public QLNumeric subtract(QLNumeric rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).subtract(rhs);
        }
        return new QLInteger(this.value.subtract((BigInteger) rhs.getValue()));
    }

    @Override
    public QLNumeric multiply(QLNumeric rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).multiply(rhs);
        }
        return new QLInteger(this.value.multiply((BigInteger) rhs.getValue()));
    }

    @Override
    public QLNumeric divide(QLNumeric rhs) {

        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).divide(rhs);
        }

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

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).equals(rhs);
        }
        return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) == 0);
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).notEquals(rhs);
        }
        return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) != 0);
    }

    @Override
    public QLBoolean greaterThan(QLNumeric rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).greaterThan(rhs);
        }
        return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) > 0);
    }

    @Override
    public QLBoolean greaterEqual(QLNumeric rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).greaterEqual(rhs);
        }
        return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) >= 0);
    }

    @Override
    public QLBoolean lessThan(QLNumeric rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).lessThan(rhs);
        }
        return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) < 0);
    }

    @Override
    public QLBoolean lessEqual(QLNumeric rhs) {
        if (rhs instanceof QLDecimal) {
            return (new QLDecimal(new BigDecimal(this.value))).lessEqual(rhs);
        }
        return new QLBoolean(this.value.compareTo((BigInteger) rhs.value) <= 0);
    }
}
