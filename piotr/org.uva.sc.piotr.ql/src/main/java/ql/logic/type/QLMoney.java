package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.math.BigDecimal;

public class QLMoney extends QLDataTypeWrapper<BigDecimal> {
    QLMoney(BigDecimal value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.MONEY;
    }

    @Override
    public QLDataTypeWrapper add(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLMoney(this.value.add(QLDecimal.castToBigDecimal(rhs.value)));
        return super.add(rhs);
    }

    @Override
    public QLDataTypeWrapper subtract(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLMoney(this.value.subtract(QLDecimal.castToBigDecimal(rhs.value)));
        return super.subtract(rhs);
    }

    @Override
    public QLDataTypeWrapper multiply(QLDataTypeWrapper rhs) {
        // money times whatever that can be casted to big decimal = money
        // money times money -> illegal
        if (rhs instanceof QLInteger || rhs instanceof QLDecimal)
            return new QLMoney(this.value.multiply(QLDecimal.castToBigDecimal(rhs.value)));
        return super.multiply(rhs);
    }

    @Override
    public QLDataTypeWrapper divide(QLDataTypeWrapper rhs) {
        // divide only by integer or decimal
        // result -> always money
        if (rhs instanceof QLInteger || rhs instanceof QLDecimal) {
            BigDecimal rhsValue = castToBigDecimal(rhs.getValue());
            if (rhsValue.compareTo(BigDecimal.ZERO) != 0) {
                return new QLMoney(this.value.divide(castToBigDecimal(rhs.getValue()), 8, BigDecimal.ROUND_DOWN));
            } else if (this.value.compareTo(BigDecimal.ZERO) >= 0) {
                // plus infinity
                return new QLMoney(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN));
            } else {
                // minus infinity
                return new QLMoney(new BigDecimal(Double.MAX_VALUE).setScale(8, BigDecimal.ROUND_DOWN).negate());
            }
        }
        return super.divide(rhs);
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLBoolean(this.value.compareTo(QLDecimal.castToBigDecimal(rhs.getValue())) == 0);
        return super.equals(rhs);
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLBoolean(this.value.compareTo(QLDecimal.castToBigDecimal(rhs.getValue())) != 0);
        return super.notEquals(rhs);
    }

    @Override
    public QLBoolean greaterThan(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLBoolean(this.value.compareTo(QLDecimal.castToBigDecimal(rhs.getValue())) > 0);
        return super.greaterThan(rhs);
    }

    @Override
    public QLBoolean greaterEqual(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLBoolean(this.value.compareTo(QLDecimal.castToBigDecimal(rhs.getValue())) >= 0);
        return super.greaterEqual(rhs);
    }

    @Override
    public QLBoolean lessThan(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLBoolean(this.value.compareTo(QLDecimal.castToBigDecimal(rhs.getValue())) < 0);
        return super.lessThan(rhs);
    }

    @Override
    public QLBoolean lessEqual(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLMoney)
            return new QLBoolean(this.value.compareTo(QLDecimal.castToBigDecimal(rhs.getValue())) <= 0);
        return super.lessEqual(rhs);
    }
}
