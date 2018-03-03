package models.ast.elements.expressions;

abstract public class Expression {
    boolean isBinary = false;

    public boolean isBinary() {
        return isBinary;
    }

    public void setBinary(boolean binary) {
        isBinary = binary;
    }
}
