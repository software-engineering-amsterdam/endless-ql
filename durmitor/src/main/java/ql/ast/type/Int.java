package ql.ast.type;

public class Int extends Numeric {

    @Override public String toString() { return "integer"; }
    @Override public boolean equals(Type t) { return t.isInteger(); }
    @Override public boolean isInteger() { return true; }
}
