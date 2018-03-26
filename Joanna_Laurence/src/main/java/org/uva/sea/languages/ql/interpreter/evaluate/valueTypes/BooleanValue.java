package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class BooleanValue extends Value {
    private final boolean booleanValue;

    public BooleanValue(final String value) {
        this.booleanValue = value.equals("true") || value.equals("TRUE");
    }

    public BooleanValue(final boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public final boolean getBooleanValue() {
        return this.booleanValue;
    }

    @Override
    public final Value and(final Value value) throws EvaluationException {
        return value.and(this);
    }

    @Override
    public final Value and(final BooleanValue value) {
        return new BooleanValue(this.booleanValue && value.booleanValue);
    }

    @Override
    public final Value isEqual(final Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public final Value isEqual(final BooleanValue value) {
        return new BooleanValue(this.booleanValue == value.booleanValue);
    }

    @Override
    public final Value isNotEqual(final Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public final Value isNotEqual(final BooleanValue value) {
        return new BooleanValue(this.booleanValue != value.booleanValue);
    }

    @Override
    public final Value or(final Value value) throws EvaluationException {
        return value.or(this);
    }

    @Override
    public final Value or(final BooleanValue value) {
        return new BooleanValue(this.booleanValue || value.booleanValue);
    }

    @Override
    public final Value not() {
        return new BooleanValue(!this.booleanValue);
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        return NodeType.BOOLEAN;
    }

    @Override
    public final String toString() {
        return this.booleanValue ? "true" : "false";
    }

    public final BooleanValue clone() throws CloneNotSupportedException {
        return (BooleanValue) super.clone();
    }
}
