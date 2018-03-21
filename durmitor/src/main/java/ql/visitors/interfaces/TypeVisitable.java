package ql.visitors.interfaces;

public interface TypeVisitable {
    
    public <T> T accept(TypeVisitor<T> visitor);
}
