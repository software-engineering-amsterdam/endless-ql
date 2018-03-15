package org.uva.sea.gui.render.visitor;

import org.uva.sea.gui.model.*;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

public class TextToValueVisitor implements QuestionModelVisitor<Value> {
    private String stringValue;

    public TextToValueVisitor(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public Value visit(BooleanQuestionModel question) {
        return new BooleanValue(this.stringValue);
    }

    @Override
    public Value visit(DateQuestionModel question) {
        return new DateValue(this.stringValue);
    }

    @Override
    public Value visit(DecimalQuestionModel question) {
        return new DecimalValue(this.stringValue);
    }

    @Override
    public Value visit(ErrorQuestionModel question) {
        return new ErrorValue(this.stringValue, 0, 0);
    }

    @Override
    public Value visit(IntQuestionModel question) {
        return new IntValue(this.stringValue);
    }

    @Override
    public Value visit(MoneyQuestionModel question) {
        return new MoneyValue(this.stringValue);
    }

    @Override
    public Value visit(StringQuestionModel question) {
        return new StringValue(this.stringValue);
    }
}
