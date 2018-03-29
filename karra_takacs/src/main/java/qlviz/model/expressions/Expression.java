package qlviz.model.expressions;

public interface Expression {
    void accept(ExpressionVisitor expressionVisitor);
    <T> T accept(TypedExpressionVisitor<T> typedExpressionVisitor);
}
