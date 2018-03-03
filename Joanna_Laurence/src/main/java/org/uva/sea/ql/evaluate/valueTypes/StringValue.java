package org.uva.sea.ql.evaluate.valueTypes;

import org.uva.sea.ql.exceptions.EvaluationException;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.visitor.BaseValueVisitor;

public class StringValue extends Value {

    private String stringValue;

    public StringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public Value isEqual(Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(StringValue value) {
        return new BooleanValue(this.stringValue.equals(value.getStringValue()));
    }

    @Override
    public Value isNotEqual(Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(StringValue value) {
        return new BooleanValue(!this.stringValue.equals(value.getStringValue()));
    }

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.STRING;
    }
}
