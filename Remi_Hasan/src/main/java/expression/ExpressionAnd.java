package expression;

import model.Form;

public class ExpressionAnd extends Expression<Boolean> {
    private Expression left;
    private Expression right;

    public ExpressionAnd(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return (boolean)left.evaluate(form) && (boolean)right.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return left.isEvaluable(form) && right.isEvaluable(form);
    }

    public String toString(Form form) {
        return left.toString() + " && " + right.toString();
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}