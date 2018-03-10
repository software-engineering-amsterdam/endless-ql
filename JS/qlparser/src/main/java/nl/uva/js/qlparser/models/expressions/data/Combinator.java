package nl.uva.js.qlparser.models.expressions.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.CompOp;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.enums.Operator;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;
import nl.uva.js.qlparser.exceptions.TypeNotPossibleException;

@Data
@Builder
public class Combinator<T> implements DataExpression {
    @NonNull private Operator operator;
    @NonNull private DataExpression<T> left;
    @NonNull private DataExpression<T> right;

    @Override
    public DataType checkAndReturnType() {
        DataType leftType = left.checkAndReturnType();
        DataType rightType = right.checkAndReturnType();

        if (!leftType.equals(rightType))
            throw new TypeMismatchException(leftType, rightType);

        if (!operator.requiredType().contains(leftType))
            throw new TypeNotPossibleException(operator.requiredType(), leftType);

        if (operator instanceof CompOp) return DataType.BOOLEAN;

        return leftType;
    }

    @Override
    public T value() {
        return (T) operator.getApply().apply(left.value(), right.value());
    }
}
