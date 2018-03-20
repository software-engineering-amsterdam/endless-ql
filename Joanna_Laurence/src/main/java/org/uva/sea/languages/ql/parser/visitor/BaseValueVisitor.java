package org.uva.sea.languages.ql.parser.visitor;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

public abstract class BaseValueVisitor<T> {

    public T visit(BooleanValue node) {
        return null;
    }

    public T visit(DateValue node) {
        return null;
    }

    public T visit(DecimalValue node) {
        return null;
    }

    public T visit(ErrorValue node) {
        return null;
    }

    public T visit(IntValue node) {
        return null;
    }

    public T visit(MoneyValue node) {
        return null;
    }

    public T visit(StringValue node) {
        return null;
    }

    public T visit(QuestionValue node) {
        return null;
    }

    public T visit(UndefinedValue node) {
        return null;
    }
}
