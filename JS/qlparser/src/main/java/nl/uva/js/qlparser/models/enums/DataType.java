package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public enum DataType {
    DATE(String::new),
    MONEY(Double::valueOf),
    STRING(String::valueOf),
    DECIMAL(Double::valueOf),
    BOOLEAN(Boolean::valueOf),
    INTEGER(Integer::valueOf);

    @NonNull @Getter private Function<String, Object> valueOf;
}
