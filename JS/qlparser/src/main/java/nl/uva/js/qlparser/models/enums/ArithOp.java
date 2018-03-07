package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum ArithOp implements Operator {
    PLUS,
    MIN,
    MULT,
    DIV;

    @Getter public BiFunction apply;
    public List<DataType> requiredType() {
        return Arrays.asList(DataType.INTEGER, DataType.DECIMAL, DataType.MONEY);
    }
}
