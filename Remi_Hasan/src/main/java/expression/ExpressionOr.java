package expression;

import model.Form;

public class ExpressionOr extends Expression<Boolean> {
    private Expression left;
    private Expression right;

    public ExpressionOr(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return (boolean)left.evaluate(form) || (boolean)right.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return left.isEvaluable(form) && right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return left.toString() + " || " + right.toString();
    }
}