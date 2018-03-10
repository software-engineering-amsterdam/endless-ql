package nl.uva.js.qlparser.models.expressions.data;

import lombok.*;
import nl.uva.js.qlparser.models.enums.DataType;

@Data
@Builder
public class Variable implements DataExpression {
    private DataType dataType;
    @NonNull private String name;
    @Setter private DataExpression value;

    @Override
    public DataType checkAndReturnType() {
        return dataType;
    }

    @Override
    public Object value() {
        return value.value();
    }
}
