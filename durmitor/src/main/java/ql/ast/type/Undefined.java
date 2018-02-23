package ql.ast.type;

public class Undefined extends Type {

    @Override public String toString() { return "undefined"; }
    @Override public boolean equals(Type t) { return t.isUndefined(); }
    @Override public boolean isUndefined() { return true; }
}
