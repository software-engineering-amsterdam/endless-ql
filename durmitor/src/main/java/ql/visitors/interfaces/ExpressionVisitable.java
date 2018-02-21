package ql.visitors.interfaces;

public interface ExpressionVisitable {
    
    public void accept(ExpressionVisitor visitor);

}
