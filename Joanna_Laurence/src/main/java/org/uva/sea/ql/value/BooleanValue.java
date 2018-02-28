package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.NodeType;

public class BooleanValue extends Value {
    boolean booleanValue;

    public BooleanValue(String value) {
        this.booleanValue = value.equals("true") || value.equals("TRUE");
    }

    public BooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    @Override
    public Value and(Value value) throws Exception {
        return value.and(this);
    }

    @Override
    public Value and(BooleanValue value) {
        return new BooleanValue(this.booleanValue && value.getBooleanValue());
    }

    @Override
    public Value isEqual(Value value) throws Exception {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(BooleanValue value) {
        return new BooleanValue(this.booleanValue == value.getBooleanValue());
    }

    @Override
    public Value isNotEqual(Value value) throws Exception {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(BooleanValue value) {
        return new BooleanValue(this.booleanValue != value.getBooleanValue());
    }

    @Override
    public Value or(Value value) throws Exception {
        return value.or(value);
    }

    @Override
    public Value or(BooleanValue value) {
        return new BooleanValue(this.booleanValue || value.getBooleanValue());
    }

    @Override
    public Value not() {
        return new BooleanValue(!this.getBooleanValue());
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.BOOLEAN;
    }
}