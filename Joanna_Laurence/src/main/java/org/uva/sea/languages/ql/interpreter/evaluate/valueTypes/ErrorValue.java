package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class ErrorValue extends Value {
    private final String error;
    private final int line;
    private final int column;

    public ErrorValue(String error, int line, int column) {
        this.error = error;
        this.line = line;
        this.column = column;
    }

    public String getError() {
        return this.error;
    }

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value negate() {
        return this;
    }

    @Override
    public Value not() {
        return this;
    }

    @Override
    public Value positive() {
        return this;
    }

    @Override
    public NodeType getType() {
        return NodeType.UNKNOWN;
    }

    @Override
    public String toString() {
        return (this.error != null) ? this.error : "No value";
    }

    public ErrorValue clone() throws CloneNotSupportedException {
        return (ErrorValue) super.clone();
    }
}
