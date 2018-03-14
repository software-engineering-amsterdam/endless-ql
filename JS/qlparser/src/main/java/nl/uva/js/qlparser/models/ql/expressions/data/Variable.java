package nl.uva.js.qlparser.models.ql.expressions.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.wrappers.logic.ValueChangeListener;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Variable implements DataExpression {
    @NonNull private DataType dataType;
    @NonNull private String name;
    private DataExpression value;
    private final List<ValueChangeListener> valueChangeListeners = new ArrayList<>();

    public void setValue(DataExpression value) {
        this.value = value;
        valueChangeListeners.forEach(listener -> listener.onChange(this));
    }

    @Override
    public DataType returnCheckedType() {
        return dataType;
    }

    @Override
    public Object value() {
        return NonNullRun.function(value, DataExpression::value);
    }

    @Override
    public void addChangeListener(ValueChangeListener listener) {
        valueChangeListeners.add(listener);
        NonNullRun.consumer(value, val -> val.addChangeListener(listener));
    }
}
