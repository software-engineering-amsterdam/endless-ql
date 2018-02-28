package nl.uva.js.qlparser.models.enums;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse, DateField::new),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.'))), TextField::new),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), TextField::new),
    DECIMAL(Double::valueOf, TextField::new),
    BOOLEAN(Boolean::valueOf, CheckBox::new),
    INTEGER(Integer::valueOf, TextField::new);

    @NonNull @Getter private Function<String, ?> valueOf;
    @NonNull @Getter private Supplier<Component> component;
}
