package expression;

public class ExpressionOr extends Expression<Boolean> {
    private final Expression left;
    private final Expression right;

    public ExpressionOr(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate() {
        if(isEvaluable()){
            return (boolean)left.evaluate() || (boolean)right.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return left.isEvaluable() && right.isEvaluable();
    }

    @Override
    public String toString() {
        return left.toString() + " || " + right.toString();
    }
}