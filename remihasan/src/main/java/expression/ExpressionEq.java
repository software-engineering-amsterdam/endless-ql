package expression;

public class ExpressionEq extends Expression<Boolean> {
    private final Expression left;
    private final Expression right;

    public ExpressionEq(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate() {
        if(isEvaluable()){
            return this.left.evaluate() == this.right.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return this.left.isEvaluable() && this.right.isEvaluable();
    }
}
