package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class ErrorValue extends Value {
    private final String error;
    private final int line;
    private final int column;

    public ErrorValue(final String error, final int line, final int column) {
        this.error = error;
        this.line = line;
        this.column = column;
    }

    public final String getError() {
        return this.error;
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final Value negate() {
        return this;
    }

    @Override
    public final Value not() {
        return this;
    }

    @Override
    public final Value positive() {
        return this;
    }

    @Override
    public final NodeType getType() {
        return NodeType.UNKNOWN;
    }

    @Override
    public final String toString() {
        return (this.error != null) ? this.error : "No value";
    }

    public final ErrorValue clone() throws CloneNotSupportedException {
        return (ErrorValue) super.clone();
    }
}
