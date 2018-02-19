package ql.ast.type;

public class Undefined extends Type {

    @Override
    public String toString() {
        return "undefined";
    }
    
    public Undefined clone() {
        return new Undefined();
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
}
