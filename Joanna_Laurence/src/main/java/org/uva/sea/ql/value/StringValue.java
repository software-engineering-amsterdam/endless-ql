package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;

public class StringValue extends Value {

    private String stringValue;

    public StringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    public Value add(StringValue value) {
        return new StringValue(this.getStringValue().concat(value.getStringValue()));
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

}
