package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.DataType;

@Data
@Builder
public class Variable<T> implements DataExpression {
    private DataType dataType;
    @NonNull private String name;

    @Override
    public DataType checkAndReturnType() {
        return dataType;
    }

    @Override
    public T value() {
        return (T) new Object();
    }
}
