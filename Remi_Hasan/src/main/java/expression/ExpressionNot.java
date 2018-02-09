package expression;

public class ExpressionNot extends Expression<Boolean> {

    private final Expression value;

    public ExpressionNot(Expression value){
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
        return value.isEvaluable();
    }
}
