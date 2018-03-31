package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;

public class QLDecimal extends QLDataTypeWrapper<BigDecimal> {

    public QLDecimal(BigDecimal value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.DECIMAL;
    }

    @Override
    public QLDecimal negate() {
        return new QLDecimal(this.value.negate());
    }

    // binary operations
    @Override
    public QLDecimal add(QLDataTypeWrapper rhs) {
        return new QLDecimal(this.value.add(castToBigDecimal(rhs.getValue())));
    }

    @Override
    public QLDecimal subtract(QLDataTypeWrapper rhs) {
        return new QLDecimal(this.value.subtract(castToBigDecimal(rhs.getValue())));
    }

    @Override
    public QLDataTypeWrapper multiply(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLDecimal || rhs instanceof QLInteger)
            return new QLDecimal(this.value.multiply(castToBigDecimal(rhs.getValue())));
        if (rhs instanceof QLMoney)
            // decimal * money -> money
            return new QLMoney(this.value.multiply(castToBigDecimal(rhs.getValue())));
        throw new RuntimeException("Illegal operation");
    }

    @Override
    public QLDecimal divide(QLDataTypeWrapper rhs) {
        BigDecimal rhsValue = castToBigDecimal(rhs.getValue());
        if (rhsValue.compareTo(BigDecimal.ZERO) != 0) {
            return new QLDecimal(this.value.divide(castToBigDecimal(rhs.getValue()), 8, BigDecimal.ROUND_DOWN));
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
        return new QLBoolean(this.value.compareTo(castToBigDecimal(rhs.getValue())) == 0);
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castToBigDecimal(rhs.getValue())) != 0);
    }

    @Override
    public QLBoolean greaterThan(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castToBigDecimal(rhs.getValue())) > 0);
    }

    @Override
    public QLBoolean greaterEqual(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castToBigDecimal(rhs.getValue())) >= 0);
    }

    @Override
    public QLBoolean lessThan(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castToBigDecimal(rhs.getValue())) < 0);
    }

    @Override
    public QLBoolean lessEqual(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.compareTo(castToBigDecimal(rhs.getValue())) <= 0);
    }

}
