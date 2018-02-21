package expression;

public class ExpressionComparisonLE extends ExpressionComparison {

    public ExpressionComparisonLE(Expression left, Expression right) {
        super(left, right, "<=");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.le(rightEvaluated);
    }
}