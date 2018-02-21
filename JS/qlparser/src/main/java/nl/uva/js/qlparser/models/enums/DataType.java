package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.')))),
    STRING(value -> String.valueOf(value).replaceAll("^\"", "").replaceAll("\"$", "")),
    DECIMAL(Double::valueOf),
    BOOLEAN(Boolean::valueOf),
    INTEGER(Integer::valueOf);

    @NonNull @Getter private Function<String, ?> valueOf;
}
