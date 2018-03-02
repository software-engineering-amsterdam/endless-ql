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
    DATE(LocalDate::parse, String::new),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.'))), String::new),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), String::new),
    DECIMAL(Double::valueOf, String::new),
    BOOLEAN(Boolean::valueOf, String::new),
    INTEGER(Integer::valueOf, String::new);

    @NonNull @Getter private Function<String, ?> valueOf;
    @NonNull private Supplier<String> component;

    public Supplier<String> getComponent() {
        return component;
    }
}