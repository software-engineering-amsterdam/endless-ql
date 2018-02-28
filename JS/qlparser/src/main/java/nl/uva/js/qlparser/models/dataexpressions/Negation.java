package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.exceptions.TypeMismatchException;

@Data
@Builder
public class Negation implements DataExpression {
    private DataExpression expression;

    @Override
    public DataType checkAndReturnType() {
        DataType expressionType = expression.checkAndReturnType();

        if (!expressionType.equals(DataType.BOOLEAN))
            throw new TypeMismatchException(DataType.BOOLEAN, expressionType);

        return DataType.BOOLEAN;
    }

    @Override
    public Boolean value() {
        return !((Boolean) expression.value());
    }
}
