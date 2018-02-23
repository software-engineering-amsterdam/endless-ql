package nl.uva.js.qlparser.models.dataexpressions;

import com.vaadin.ui.Component;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.DataType;

import java.util.ArrayList;

@Data
@Builder
public class Value implements DataExpression {
    @NonNull private DataType dataType;
    @NonNull private Object value;

    @Override
    public ArrayList<Component> getComponents() {

        return new ArrayList<>();
    }

    @Override
    public DataType checkAndReturnType() {
        return dataType;
    }
}
