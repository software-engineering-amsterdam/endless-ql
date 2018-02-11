package expression;

import model.Form;

public class ExpressionGE extends Expression<Boolean>{

    private Expression left;
    private Expression right;

    public ExpressionGE(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return (double)this.left.evaluate(form) >= (double)this.right.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isNumber(form) && this.right.isNumber(form) && this.left.isEvaluable(form) && this.right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return left.toString() + " >= " + right.toString();
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}
