package nl.uva.js.qlparser.models.ql.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.helpers.ArithHelpers;
import nl.uva.js.qlparser.wrappers.arithmetic.Calculatable;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public enum ArithOp implements Operator {
    PLUS(ArithHelpers::plus),
    MIN(ArithHelpers::min),
    MULT(ArithHelpers::mult),
    DIV(ArithHelpers::div);

    @NonNull @Getter public BiFunction<? extends Calculatable, ? extends Calculatable, ? extends Calculatable> apply;
    public List<DataType> requiredType() {
        return Arrays.asList(DataType.INTEGER, DataType.DECIMAL, DataType.MONEY);
    }
}
