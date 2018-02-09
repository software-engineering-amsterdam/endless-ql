package expression;

public class ExpressionNeg extends Expression<Double> {
    private final Expression value;

    public ExpressionNeg(Expression value) {
        this.value = value;
    }

    @Override
    public Double evaluate() {
        if(isEvaluable()){
            return -(double)this.value.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return this.value.isEvaluable();
    }
}