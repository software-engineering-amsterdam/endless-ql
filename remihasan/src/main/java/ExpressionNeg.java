public class ExpressionNeg extends Expression<Boolean> {
    private final Expression value;

    public ExpressionNeg(Expression value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate() {
        if(isEvaluable()){
            return !(boolean)this.value.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return this.value.isEvaluable();
    }
}