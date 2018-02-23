package ql.ast.type;

import ql.ast.QLNode;

public abstract class Type extends QLNode {

    public abstract String toString();
    public abstract void setName(String name);
    public abstract String getName();
    public abstract void setValue(String value);
    
    public boolean equals(Type t) {
        return this.toString().equals(t.toString());
    }
    
    public boolean isBoolean() {
        return false;
    }
    
    public boolean isDate() {
        return false;
    }
    
    public boolean isDecimal() {
        return false;
    }
    
    public boolean isInt() {
        return false;
    }
    
    public boolean isMoney() {
        return false;
    }
    
    public boolean isNumeric() {
        return false;
    }
    
    public boolean isStr() {
        return false;
    }
    
    public boolean isUndefined() {
        return false;
    }
}
