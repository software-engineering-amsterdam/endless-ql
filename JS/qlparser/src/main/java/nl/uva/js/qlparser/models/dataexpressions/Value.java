package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.DataType;

@Data
@Builder
public class Value implements DataExpression {
    @NonNull private DataType dataType;
    @NonNull private Object value;

    @Override
    public void toRepresentation() {

    }

    @Override
    public DataType checkAndReturnType() {
        return dataType;
    }
}
