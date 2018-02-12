package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.enums.DataType;

@Data
@Builder
public class Value implements DataExpression {
    private DataType dataType;

    @Override
    public void toRepresentation() {

    }
}
