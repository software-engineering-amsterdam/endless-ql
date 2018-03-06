package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse, TextField::new),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.'))), TextField::new),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), TextField::new),
    DECIMAL(Double::valueOf, TextField::new),
    BOOLEAN(Boolean::valueOf, Checkbox::new),
    INTEGER(Integer::valueOf, TextField::new);

    @NonNull @Getter private Function<String, ?> valueOf;
    @NonNull @Getter private Supplier<Component> component;
}