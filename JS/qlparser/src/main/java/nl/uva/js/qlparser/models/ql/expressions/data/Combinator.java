package nl.uva.js.qlparser.models.ql.expressions.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.ql.enums.CompOp;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.enums.Operator;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;
import nl.uva.js.qlparser.exceptions.TypeNotPossibleException;
import nl.uva.js.qlparser.wrappers.logic.ValueChangeListener;

@Data
@Builder
public class Combinator<T> implements DataExpression {
    @NonNull private Operator operator;
    @NonNull private DataExpression<T> left;
    @NonNull private DataExpression<T> right;

    @Override
    public DataType returnCheckedType() {
        DataType leftType = left.returnCheckedType();
        DataType rightType = right.returnCheckedType();

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

    @Override
    public void addChangeListener(ValueChangeListener listener) {
        left.addChangeListener(listener);
        right.addChangeListener(listener);
    }
}
