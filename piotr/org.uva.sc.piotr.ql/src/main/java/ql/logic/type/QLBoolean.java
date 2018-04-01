package ql.logic.type;

import ql.ast.model.expressions.Expression;

public class QLBoolean extends QLDataTypeWrapper<Boolean> {

    public QLBoolean(Boolean value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.BOOLEAN;
    }

    @Override
    public QLBoolean negate() {
        return new QLBoolean(!this.value);
    }

    @Override
    public QLBoolean and(QLBoolean rhs) {
        return new QLBoolean(this.value && rhs.value);
    }

    @Override
    public QLBoolean or(QLBoolean rhs) {
        return new QLBoolean(this.value || rhs.value);
    }
}