package nl.uva.js.qlparser.models.enums;

import java.util.Arrays;
import java.util.List;

public enum ArithOp implements Operator {
    PLUS,
    MIN,
    MULT,
    DIV;

    public List<DataType> requiredType() {
        return Arrays.asList(DataType.INTEGER, DataType.DECIMAL, DataType.MONEY);
    }
}
