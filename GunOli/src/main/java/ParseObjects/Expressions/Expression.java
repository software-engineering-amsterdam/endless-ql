package ParseObjects.Expressions;

public abstract class Expression<T> {
    public abstract EvaluationType returnType();
    public abstract T evaluate();
}