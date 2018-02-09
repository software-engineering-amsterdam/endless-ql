package expression;

public class ExpressionSum extends Expression<Double> {

    private final Expression left;
    private final Expression right;

    public ExpressionSum(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Double evaluate() {
        if(isEvaluable()){
            return (double)left.evaluate() + (double)right.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return left.isEvaluable() && right.isEvaluable();
    }

    @Override
    public String toString() {
        return this.left.toString() + " + " + this.right.toString();
    }

}