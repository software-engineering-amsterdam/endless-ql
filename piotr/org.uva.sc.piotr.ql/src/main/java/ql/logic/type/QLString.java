package ql.logic.type;

import ql.ast.model.expressions.Expression;

public class QLString extends QLSummable<String> {
    public QLString(String value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.STRING;
    }

    @Override
    public QLString add(QLSummable rhs) {
        return new QLString(this.value + rhs.value);
    }

    @Override
    public QLBoolean equals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLString) {
            return new QLBoolean(this.value.equals(rhs.value));
        }
        throw new RuntimeException("Incompatible types comparision: STRING and " + rhs.getType());
    }

    @Override
    public QLBoolean notEquals(QLDataTypeWrapper rhs) {
        if (rhs instanceof QLString) {
            return new QLBoolean(!this.value.equals(rhs.value));
        }
        throw new RuntimeException("Incompatible types comparision: STRING and " + rhs.getType());
    }
}