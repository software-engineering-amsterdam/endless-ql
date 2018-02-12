package expression;

import model.Form;

public abstract class ExpressionComparison extends Expression<Boolean>{
    Expression left;
    Expression right;

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isNumber(form) && this.right.isNumber(form) && this.left.isEvaluable(form) && this.right.isEvaluable(form);
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}
