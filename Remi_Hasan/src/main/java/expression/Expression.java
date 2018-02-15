package expression;

public abstract class Expression<T> {

    public abstract ReturnType getReturnType();

    public abstract ExpressionVariable evaluate();

    public boolean isSetable() {
        return false;
    }

    public void setValue(String value) {

    }
}