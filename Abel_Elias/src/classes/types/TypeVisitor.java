package classes.types;

public interface TypeVisitor<T> {

    T visit(ExpressionType type);
}
