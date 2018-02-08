public class ExpressionDivide extends Expression<Double> {
    private final Expression left;
    private final Expression right;

    ExpressionDivide(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Double evaluate() {
        if(isEvaluable() && (double)right.evaluate() != 0){
            return (double)left.evaluate() * (double)right.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return left.isEvaluable() && right.isEvaluable();
    }

    @Override
    public String toString() {
        return left.toString() + " / " + right.toString();
    }
}
