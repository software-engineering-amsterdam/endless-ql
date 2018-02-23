package ql.value;

import ql.ast.type.Type;

public abstract class Value<T> {
    
    public abstract T getValue();
    public abstract Type getType();
    
}
