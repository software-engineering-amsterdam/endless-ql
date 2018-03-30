package ql.logic.type;

import ql.ast.model.expressions.Expression;

public class QLBoolean extends QLDataTypeWrapper<Boolean> {

    public QLBoolean(Boolean value) {
        super(value);
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        return new QLBoolean(this.value.equals(rhs.value));
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        return new QLBoolean(!this.value.equals(rhs.value));
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.BOOLEAN;
    }

    public QLBoolean negate() {
        return new QLBoolean(!this.value);
    }

    public QLBoolean and(QLBoolean rhs) {
        return new QLBoolean(this.value && rhs.value);
    }

    public QLBoolean or(QLBoolean rhs) {
        return new QLBoolean(this.value || rhs.value);
    }
}