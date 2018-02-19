package ql.ast.type;

public class Bool extends Type {

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public boolean isBoolean() { 
        return true; 
    }
}
