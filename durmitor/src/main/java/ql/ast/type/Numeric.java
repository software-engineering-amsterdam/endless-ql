package ql.ast.type;

public abstract class Numeric extends Type {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
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
