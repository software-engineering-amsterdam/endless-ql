package expression;

public class ExpressionLogicalOr extends ExpressionLogical {

    public ExpressionLogicalOr(Expression left, Expression right) {
        super(left, right, "||");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.or(rightEvaluated);
    }
}