package nl.uva.js.qlparser.models.ql.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;
import nl.uva.js.qlparser.wrappers.arithmetic.CalculatableDouble;
import nl.uva.js.qlparser.wrappers.arithmetic.CalculatableInteger;
import nl.uva.js.qlparser.wrappers.arithmetic.CalculatableMoney;

import javax.swing.*;
import java.time.LocalDate;
import java.util.function.Function;

@RequiredArgsConstructor
public enum DataType {
    DATE(LocalDate::parse, ComponentBuilder::buildTextField),
//    To be improved at a later stage, but needed for type checking
    MONEY(CalculatableMoney::new, ComponentBuilder::buildTextField),
    STRING(value -> String.valueOf(value).replaceAll("^\"|\"$", ""), ComponentBuilder::buildTextField),
    DECIMAL(CalculatableDouble::new, ComponentBuilder::buildTextField),
    BOOLEAN(Boolean::parseBoolean, ComponentBuilder::buildCheckBox),
    INTEGER(CalculatableInteger::new, ComponentBuilder::buildTextField);

    @NonNull @Getter private Function<String, ?> valueOf;
    @NonNull @Getter private Function<Variable, ? extends JComponent> component;
}