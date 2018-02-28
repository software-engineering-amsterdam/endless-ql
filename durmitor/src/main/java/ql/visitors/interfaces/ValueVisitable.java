package ql.visitors.interfaces;

import ql.evaluator.value.Value;

public interface ValueVisitable {
    
    public Value<?> accept(ValueVisitor visitor);
}
