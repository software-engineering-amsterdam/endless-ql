package nl.uva.js.qlparser.models.enums;

import java.util.Arrays;
import java.util.List;

public enum CompOp implements Operator {
    LT,
    GT,
    LTE,
    GTE,

    EQ,
    NEQ;

    public List<DataType> requiredType() {
        return Arrays.asList(DataType.values());
    }
}
