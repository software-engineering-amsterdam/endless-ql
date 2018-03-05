package ql.ast.type;

public abstract class Numeric extends Type {

    @Override
    public String toString() {
        return "numeric";
    }

    @Override
    public boolean equals(Type t) {
        return false;
    }

    @Override
    public boolean isNumeric() {
        return true;
    }
}
