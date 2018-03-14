package nl.uva.js.qlparser.models.expressions.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.wrappers.logic.ValueChangeListener;

@Data
@Builder
public class Value<T> implements DataExpression {
    @NonNull private DataType dataType;
    @NonNull private T value;

    @Override
    public DataType returnCheckedType() {
        return dataType;
    }

    @Override
    public T value() {
        return this.getValue();
    }

    @Override
    public void addChangeListener(ValueChangeListener ignored) {}
}
