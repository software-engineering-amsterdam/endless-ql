package expression;

public class ExpressionArithmeticSubtract extends ExpressionArithmetic {

    public ExpressionArithmeticSubtract(Expression left, Expression right) {
        super(left, right, "-");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.subtract(rightEvaluated);
    }
}
