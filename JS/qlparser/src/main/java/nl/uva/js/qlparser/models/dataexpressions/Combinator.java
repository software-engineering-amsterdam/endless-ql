package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.BoolOp;
import nl.uva.js.qlparser.models.enums.CompOp;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.enums.Operator;
import nl.uva.js.qlparser.models.exceptions.TypeMismatchException;
import nl.uva.js.qlparser.models.exceptions.TypeNotPossibleException;

import java.util.Arrays;

@Data
@Builder
public class Combinator implements DataExpression {
    @NonNull private Operator operator;
    @NonNull private DataExpression left;
    @NonNull private DataExpression right;

    @Override
    public void toRepresentation() {

    }

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
}
