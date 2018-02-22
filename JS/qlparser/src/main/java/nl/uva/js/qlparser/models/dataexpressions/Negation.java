package nl.uva.js.qlparser.models.dataexpressions;

import com.vaadin.ui.Component;
import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.exceptions.TypeMismatchException;

import java.util.ArrayList;

@Data
@Builder
public class Negation implements DataExpression {
    private DataExpression expression;

    @Override
    public ArrayList<Component> getComponents() {

        return new ArrayList<>();
    }

    @Override
    public DataType checkAndReturnType() {
        DataType expressionType = expression.checkAndReturnType();

        if (!expressionType.equals(DataType.BOOLEAN))
            throw new TypeMismatchException(DataType.BOOLEAN, expressionType);

        return DataType.BOOLEAN;
    }
}
