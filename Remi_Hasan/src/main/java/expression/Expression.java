package expression;

import astvisitor.BaseASTVisitor;
import astvisitor.Value;

public abstract class Expression<T> {

    public abstract Value accept(BaseASTVisitor visitor);

}