package ql.logic.type;

import ql.ast.model.expressions.Expression;
import ql.exceptions.IllegalOperationOnTypesException;
import ql.exceptions.IllegalValueAssignmentException;

import java.math.BigDecimal;

public class QLDataTypeBoolean extends QLDataType<Boolean> {

    public QLDataTypeBoolean(Boolean value) {
        super(value);
    }

    @Override
    public QLDataTypeBoolean equals(QLDataType rhs) {
        return new QLDataTypeBoolean(this.value.equals(rhs.value));
    }

    @Override
    public QLDataTypeBoolean notEquals(QLDataType rhs) {
        return new QLDataTypeBoolean(!this.value.equals(rhs.value));
    }

    @Override
    public Expression.DataType getType() {
        return Expression.DataType.BOOLEAN;
    }

    public QLDataTypeBoolean negate() {
        return new QLDataTypeBoolean(!this.value);
    }

    public QLDataTypeBoolean and(QLDataTypeBoolean rhs) {
        return new QLDataTypeBoolean(this.value && rhs.value);
    }

    public QLDataTypeBoolean or(QLDataTypeBoolean rhs) {
        return new QLDataTypeBoolean(this.value || rhs.value);
    }
}