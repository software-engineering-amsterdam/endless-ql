package ql.ast.type;

import ql.ast.QLNode;

public abstract class Type extends QLNode {

    public abstract String toString();

    public boolean equals(Type t) {
        return this.toString().equals(t.toString());
    }
    
    public boolean isBoolean() {
        return false;
    }
    
    public boolean isUndefined() {
        return false;
    }
}
