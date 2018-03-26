package org.uva.sea.languages.ql.parser.visitor;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

public abstract class BaseValueVisitor<T> {

    public T visit(final BooleanValue node) {
        return null;
    }

    public T visit(final DateValue node) {
        return null;
    }

    public T visit(final DecimalValue node) {
        return null;
    }

    public T visit(final ErrorValue node) {
        return null;
    }

    public T visit(final IntValue node) {
        return null;
    }

    public T visit(final MoneyValue node) {
        return null;
    }

    public T visit(final StringValue node) {
        return null;
    }

    public final T visit(final QuestionValue node) {
        return null;
    }

    public final T visit(final UndefinedValue node) {
        return null;
    }
}
