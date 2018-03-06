package nl.uva.js.qlparser.models.expressions.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.DataType;

@Data
@Builder
public class Value<T> implements DataExpression {
    @NonNull private DataType dataType;
    @NonNull private T value;

    @Override
    public DataType checkAndReturnType() {
        return dataType;
    }

    @Override
    public T value() {
        return value;
    }
}
