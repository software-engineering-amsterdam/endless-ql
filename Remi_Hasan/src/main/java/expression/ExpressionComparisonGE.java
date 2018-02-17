package expression;

public class ExpressionComparisonGE extends ExpressionComparison {

    public ExpressionComparisonGE(Expression left, Expression right) {
        super(left, right, ">=");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.ge(rightEvaluated);
    }
}
