package nl.uva.js.qlparser.models.enums;

import java.util.Collections;
import java.util.List;

public enum BoolOp implements Operator {
    OR,
    AND;

    public List<DataType> requiredType() {
        return Collections.singletonList(DataType.BOOLEAN);
    }
}
