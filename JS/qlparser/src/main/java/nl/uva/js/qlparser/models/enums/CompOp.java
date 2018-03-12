package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public enum CompOp implements Operator {
    LT((a,b) -> CompareToBuilder.reflectionCompare(a, b) < 0),
    GT((a,b) -> CompareToBuilder.reflectionCompare(a, b) > 0),
    LTE((a,b) -> CompareToBuilder.reflectionCompare(a, b) <= 0),
    GTE((a,b) -> CompareToBuilder.reflectionCompare(a, b) >= 0),

    EQ((a,b) -> CompareToBuilder.reflectionCompare(a, b) == 0),
    NEQ((a,b) -> CompareToBuilder.reflectionCompare(a, b) != 0);

    @NonNull @Getter public BiFunction apply;
    public List<DataType> requiredType() {
        return Arrays.asList(DataType.values());
    }
}
