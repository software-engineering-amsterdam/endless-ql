package org.uva.sea.ql.evaluate;

public abstract class Value<T> {

    public Value add(Value value){
        return value.add(this);
    }
}
