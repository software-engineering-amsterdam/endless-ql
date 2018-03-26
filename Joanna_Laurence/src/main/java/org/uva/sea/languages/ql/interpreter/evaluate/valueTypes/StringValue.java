package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class StringValue extends Value {

    private final String stringValue;

    public StringValue(final String stringValue) {
        this.stringValue = stringValue;
    }

    public final String getStringValue() {
        return this.stringValue;
    }

    @Override
    public final Value isEqual(final Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public final Value isEqual(final StringValue value) {
        return new BooleanValue(this.stringValue.equals(value.stringValue));
    }

    @Override
    public final Value isNotEqual(final Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public final Value isNotEqual(final StringValue value) {
        return new BooleanValue(!this.stringValue.equals(value.stringValue));
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        return NodeType.STRING;
    }

    @Override
    public final String toString() {
        return this.stringValue;
    }

    public final StringValue clone() throws CloneNotSupportedException {
        return (StringValue) super.clone();
    }
}
