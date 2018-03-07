package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse, JTextField::new),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.'))), JTextField::new),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), JTextField::new),
    DECIMAL(Double::valueOf, JTextField::new),
    BOOLEAN(Boolean::valueOf, JCheckBox::new),
    INTEGER(Integer::valueOf, JTextField::new);

    @NonNull @Getter private Function<String, ?> valueOf;
    @NonNull @Getter private Supplier<? extends JComponent> component;
}