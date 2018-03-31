package ql.logic.type;

import ql.ast.model.expressions.Expression;

import java.time.LocalDate;


public class QLDate extends QLDataTypeWrapper<LocalDate> {

    QLDate(LocalDate value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.DATE;
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                this.getType() == rhs.getType()
                        && this.value.equals(rhs.getValue())
        );
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                !this.value.equals(rhs.getValue())
                        || this.getType() != rhs.getType());
    }

    @Override
    public QLBoolean greaterThan(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                this.getType() == rhs.getType()
                        && this.value.isAfter((LocalDate) rhs.getValue())

        );
    }

    @Override
    public QLBoolean greaterEqual(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                this.getType() == rhs.getType()
                        && (
                        this.value.isAfter((LocalDate) rhs.getValue())
                                || this.value.equals(rhs.getValue())
                )
        );
    }

    @Override
    public QLBoolean lessThan(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                this.getType() == rhs.getType()
                        && this.value.isBefore((LocalDate) rhs.getValue())
        );
    }

    @Override
    public QLBoolean lessEqual(QLDataTypeWrapper rhs) {
        return new QLBoolean(
                this.getType() == rhs.getType()
                        && (
                        this.value.isBefore((LocalDate) rhs.getValue())
                                || this.value.equals(rhs.getValue())
                )
        );
    }
}
