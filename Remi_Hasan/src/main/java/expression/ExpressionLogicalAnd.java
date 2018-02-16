package expression;

public class ExpressionLogicalAnd extends ExpressionLogical {

    public ExpressionLogicalAnd(Expression left, Expression right) {
        super(left, right, "&&");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.and(rightEvaluated);
    }
}