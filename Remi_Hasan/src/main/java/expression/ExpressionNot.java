package expression;

import model.Form;

public class ExpressionNot extends Expression<Boolean> {

    private final Expression value;

    public ExpressionNot(Expression value){
        this.value = value;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return !(boolean)this.value.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.value.isBoolean(form) && this.value.isEvaluable(form);
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}
