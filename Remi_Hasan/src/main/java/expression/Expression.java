package expression;

public abstract class Expression<T> {

    public abstract ReturnType getReturnType();

    public abstract ExpressionVariable evaluate();

    public boolean isSettable() {
        return false;
    }

    public void setValue(String value) {

    }
}