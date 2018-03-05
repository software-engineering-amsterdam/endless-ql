package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum DataType {
    // TODO
    DATE(LocalDate::parse, "date"),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.'))), "money"),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), "string"),
    DECIMAL(Double::valueOf, "decimal"),
    BOOLEAN(Boolean::valueOf, "boolean"),
    INTEGER(Integer::valueOf, "integer");

    @NonNull @Getter private Function<String, ?> valueOf;
    @NonNull private String typeString;

    public String getTypeString() {
        return typeString;
    }
}