package nl.uva.js.qlparser.models.ql.expressions.data;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;
import nl.uva.js.qlparser.wrappers.logic.ValueChangeListener;

@Data
@Builder
public class Negation implements DataExpression {
    private DataExpression expression;

    @Override
    public DataType returnCheckedType() {
        DataType expressionType = expression.returnCheckedType();

        if (!expressionType.equals(DataType.BOOLEAN))
            throw new TypeMismatchException(DataType.BOOLEAN, expressionType);

        return DataType.BOOLEAN;
    }

    @Override
    public Boolean value() {
        return !((Boolean) expression.value());
    }

    @Override
    public void addChangeListener(ValueChangeListener listener) {
        expression.addChangeListener(listener);
    }
}
