package ql.logic.type;

import ql.ast.model.expressions.Expression;

public class QLString extends QLDataTypeWrapper<String> {
    public QLString(String value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.STRING;
    }

    @Override
    public QLDataTypeWrapper add(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLString)
            return new QLString(this.value + rhs.value);
        return super.add(rhs);
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLString)
            return new QLBoolean(this.value.equals(rhs.value));
        return super.equals(rhs);
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLString)
            return new QLBoolean(!this.value.equals(rhs.value));
        return super.notEquals(rhs);
    }
}