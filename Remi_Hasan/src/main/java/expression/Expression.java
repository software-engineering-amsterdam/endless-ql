package expression;

public abstract class Expression<T> {

    public abstract ReturnType getReturnType();

    public abstract ExpressionVariable evaluate();

    public boolean isSettable() {
        return false;
    }

    public void setValue(String value) {

    }

    @Override
    public boolean equals(Object other){
        return this.toString().equals(other.toString());
    }
}