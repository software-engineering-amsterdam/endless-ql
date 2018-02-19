package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;

import java.time.LocalDate;
import java.util.function.Function;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse),
//    To be improved at a later stage, but needed for type checking
    MONEY(m -> Double.valueOf(m.replace(',', '.'))),
    STRING(String::valueOf),
    DECIMAL(Double::valueOf),
    BOOLEAN(Boolean::valueOf),
    INTEGER(Integer::valueOf);

    @NonNull @Getter private Function<String, Object> valueOf;
}
