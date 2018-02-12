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
            return -1 * Double.parseDouble(this.value.evaluate(form).toString());
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.value.isNumber(form) && this.value.isEvaluable(form);
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}