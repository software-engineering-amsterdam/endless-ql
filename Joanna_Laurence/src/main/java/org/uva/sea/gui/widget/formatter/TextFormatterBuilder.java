package org.uva.sea.gui.widget.formatter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.*;
import org.uva.sea.gui.model.BaseQuestionModel;

import java.util.function.UnaryOperator;

public class TextFormatterBuilder<Value> {

    private StringConverter converter;
    private UnaryOperator<TextFormatter.Change> inputFilter;
    private Value defaultValue;
    private BaseQuestionModel questionModel;

    TextFormatterBuilder<Value> addDefaultValue(Value defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    TextFormatterBuilder<Value> addInputFilter(String pattern) {
        this.inputFilter = change -> {
            String text = change.getText();

            if (text.matches(pattern)) {
                return change;
            } else {
                System.out.println("Invalid input");
            }

            return null;
        };
        return this;
    }

    TextFormatterBuilder<Value> addBooleanConverter() {
        this.converter = new BooleanStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addDecimalConverter() {
        this.converter = new BigDecimalStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addIntegerConverter() {
        this.converter = new IntegerStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addDateConverter() {
        this.converter = new DateStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addMoneyConverter() {
        this.converter = new CurrencyStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addStringConverter() {
        this.converter = new ShortStringConverter();
        return this;
    }

    TextFormatter<Value> build() {
        return new TextFormatter<Value>(this.converter, this.defaultValue, this.inputFilter);
    }
}
