package expression;

import model.Form;

public class ExpressionGT extends Expression<Boolean>{

    private Expression left;
    private Expression right;

    public ExpressionGT(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            double leftEvaluated = Double.parseDouble(this.left.evaluate(form).toString());
            double rightEvaluated = Double.parseDouble(this.right.evaluate(form).toString());
            return leftEvaluated > rightEvaluated;
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isNumber(form) && this.right.isNumber(form) && this.left.isEvaluable(form) && this.right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return left.toString() + " > " + right.toString();
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}
