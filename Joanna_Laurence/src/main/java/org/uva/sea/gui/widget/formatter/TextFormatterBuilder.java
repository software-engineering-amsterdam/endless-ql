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

    public TextFormatterBuilder() {
    }

    TextFormatterBuilder<Value> addDefaultValue(Value defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    TextFormatterBuilder<Value> addInputFilter(String pattern) {
        inputFilter = change -> {
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
        converter = new BooleanStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addDecimalConverter() {
        converter = new BigDecimalStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addIntegerConverter() {
        converter = new IntegerStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addDateConverter() {
        converter = new DateStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addMoneyConverter() {
        converter = new CurrencyStringConverter();
        return this;
    }

    TextFormatterBuilder<Value> addStringConverter() {
        converter = new ShortStringConverter();
        return this;
    }

    TextFormatter<Value> build() {
        return new TextFormatter<Value>(converter, defaultValue, inputFilter);
    }
}
