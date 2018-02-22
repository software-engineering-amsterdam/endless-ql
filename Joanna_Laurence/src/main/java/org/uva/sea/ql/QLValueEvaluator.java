package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.*;

public abstract class QLValueEvaluator<T> {

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
}
