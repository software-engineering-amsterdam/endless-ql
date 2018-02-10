package expression;

import model.Form;

public class ExpressionNeg extends Expression<Double> {
    private final Expression value;

    public ExpressionNeg(Expression value) {
        this.value = value;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form)){
            return -(double)this.value.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.value.isEvaluable(form);
    }
}