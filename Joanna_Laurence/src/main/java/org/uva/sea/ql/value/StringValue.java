package org.uva.sea.ql.value;

import org.uva.sea.ql.visitor.BaseValueVisitor;
import org.uva.sea.ql.exceptions.EvaluationException;
import org.uva.sea.ql.parser.NodeType;

public class StringValue extends Value {

    private String stringValue;

    public StringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public Value add(Value value) throws EvaluationException {
        return value.add(this);
    }

    @Override
    public Value add(StringValue value) {
        return new StringValue(this.getStringValue().concat(value.getStringValue()));
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
