package expression;

import model.Form;

public class ExpressionSum extends Expression<Double> {

    private Expression left;
    private Expression right;

    public ExpressionSum(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form)){
            return (double)left.evaluate(form) + (double)right.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return left.isEvaluable(form) && right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return this.left.toString() + " + " + this.right.toString();
    }

}