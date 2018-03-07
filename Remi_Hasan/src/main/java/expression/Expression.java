package expression;

import astvisitor.IASTVisitor;

public abstract class Expression {

    public abstract ReturnType getReturnType();

    public abstract <T> T accept(IASTVisitor<T> visitor);

}