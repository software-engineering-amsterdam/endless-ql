package org.uva.sea.gui.widget.formatter;

import javafx.scene.control.TextFormatter;
import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

public class TextFormatterVisitor implements IQuestionModelVisitor<TextFormatter> {

    @Override
    public TextFormatter visit(BooleanQuestionModel question) {
        System.out.println("Visitor Boolean");
        return new TextFormatterBuilder<BooleanValue>().addBooleanConverter().addDefaultValue((BooleanValue) question.getValue()).build();
    }

    @Override
    public TextFormatter visit(DateQuestionModel question) {
        System.out.println("Visitor Date");
        return new TextFormatterBuilder<DateValue>().addDateConverter().addDefaultValue((DateValue) question.getValue()).addInputFilter("@[1-3]{0,1}[0-9]{1} 1{0,1}[0-9]{1} \\d{4}@$").build();
    }

    @Override
    public TextFormatter visit(DecimalQuestionModel question) {
        System.out.println("Visitor Decimal");
        return new TextFormatterBuilder<DecimalValue>().addDecimalConverter().addDefaultValue((DecimalValue) question.getValue()).addInputFilter("^(\\d*\\.)?\\d+$").build();
    }

    @Override
    public TextFormatter visit(ErrorQuestionModel question) {
        //TODO: Create ErrorQuestionModel
        return null;
    }

    @Override
    public TextFormatter visit(IntQuestionModel question) {
        System.out.println("Visitor Integer");
        return new TextFormatterBuilder<IntValue>().addIntegerConverter().addDefaultValue((IntValue) question.getValue()).addInputFilter("[0-9]*").build();
    }

    @Override
    public TextFormatter visit(MoneyQuestionModel question) {
        System.out.println("Visitor Money");
        return new TextFormatterBuilder<MoneyValue>().addMoneyConverter().addDefaultValue((MoneyValue) question.getValue()).addInputFilter("^(\\d*\\.)?\\d+$").build();
    }

    @Override
    public TextFormatter visit(StringQuestionModel question) {
        System.out.println("Visitor String");
        return new TextFormatterBuilder<StringValue>().addMoneyConverter().addDefaultValue((StringValue) question.getValue()).build();
    }
}
