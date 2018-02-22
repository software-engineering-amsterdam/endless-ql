package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.traverse.Visitor;

public abstract class Value {

    public abstract <T> T accept(QLValueEvaluator<T> visitor);

    public Value add(Value value) {
        return value.add(this);
    }
}
