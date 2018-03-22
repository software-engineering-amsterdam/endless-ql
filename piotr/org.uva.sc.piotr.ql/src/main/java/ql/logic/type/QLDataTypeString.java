package ql.logic.type;

import ql.ast.model.expressions.Expression;

public class QLDataTypeString extends QLDataTypeSummable<String> {
    public QLDataTypeString(String value) {
        super(value);
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.STRING;
    }

    @Override
    public QLDataTypeString add(QLDataTypeSummable rhs) {
        return new QLDataTypeString(this.value + rhs.value);
    }

    @Override
    public QLDataTypeBoolean equals(QLDataType rhs) {
        return new QLDataTypeBoolean(this.value.equals(rhs.value));
    }

    @Override
    public QLDataTypeBoolean notEquals(QLDataType rhs) {
        return new QLDataTypeBoolean(!this.value.equals(rhs.value));
    }
}