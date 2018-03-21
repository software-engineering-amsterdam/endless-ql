package org.uva.sea.gui.widget.formatter;

import javafx.scene.control.TextFormatter;
import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.visitor.QuestionModelVisitor;

public class TextFormatterVisitor implements QuestionModelVisitor<TextFormatter> {
    @Override
    public TextFormatter visit(BooleanQuestionModel question) {
        return new TextFormatterBuilder().addBooleanConverter().addDefaultValue(question.getValue()).build();
    }

    @Override
    public TextFormatter visit(DateQuestionModel question) {
        return new TextFormatterBuilder().addDateConverter().addDefaultValue(question.getValue()).addInputFilter("@[1-3]{0,1}[0-9]{1} 1{0,1}[0-9]{1} \\d{4}@$").build();
    }

    @Override
    public TextFormatter visit(DecimalQuestionModel question) {
        return new TextFormatterBuilder().addDecimalConverter().addDefaultValue(question.getValue()).addInputFilter("^(\\d*\\.)?\\d+$").build();
    }

    @Override
    public TextFormatter visit(ErrorQuestionModel question) {
        //TODO: Create ErrorQuestionModel
        return null;
    }

    @Override
    public TextFormatter visit(IntQuestionModel question) {
        return new TextFormatterBuilder().addIntegerConverter().addDefaultValue(question.getValue()).addInputFilter("[0-9]*").build();
    }

    @Override
    public TextFormatter visit(MoneyQuestionModel question) {
        return new TextFormatterBuilder().addMoneyConverter().addDefaultValue(question.getValue()).addInputFilter("^(\\d*\\.)?\\d+$").build();
    }

    @Override
    public TextFormatter visit(StringQuestionModel question) {
        return new TextFormatterBuilder().addMoneyConverter().addDefaultValue(question.getValue()).build();
    }
}
