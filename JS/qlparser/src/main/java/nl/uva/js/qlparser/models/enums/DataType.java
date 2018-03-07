package nl.uva.js.qlparser.models.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.ui.ComponentBuilder;
import nl.uva.js.qlparser.models.expressions.data.Variable;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse, ComponentBuilder::buildTextField),
//    To be improved at a later stage, but needed for type checking
    MONEY(value -> BigDecimal.valueOf(Double.valueOf(value.replace(',', '.'))), ComponentBuilder::buildTextField),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), ComponentBuilder::buildTextField),
    DECIMAL(Double::valueOf, ComponentBuilder::buildTextField),
    BOOLEAN(Boolean::valueOf, ComponentBuilder::buildTextField),
    INTEGER(Integer::valueOf, ComponentBuilder::buildTextField);

    @NonNull @Getter private Function<String, ?>                      valueOf;
    @NonNull @Getter private Function<Variable, ? extends JComponent> component;
}