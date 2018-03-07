package astvisitor;

public abstract class Value<T> {

    public abstract Value divide(BaseASTVisitor visitor, Value other);

}


