package nl.uva.js.qlparser.models.ql.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public enum BoolOp implements Operator {
    OR((a,b) -> (Boolean) a || (Boolean) b),
    AND((a,b) -> (Boolean) a && (Boolean) b);

    @NonNull @Getter public BiFunction apply;
    public List<DataType> requiredType() {
        return Collections.singletonList(DataType.BOOLEAN);
    }
}
