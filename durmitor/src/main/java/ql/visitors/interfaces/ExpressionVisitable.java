package ql.visitors.interfaces;

public interface ExpressionVisitable {
    
    public <E> E accept(ExpressionVisitor<E> visitor);

}
