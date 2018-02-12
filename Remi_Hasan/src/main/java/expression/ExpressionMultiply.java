package expression;

import model.Form;

public class ExpressionMultiply extends Expression<Double> {
    private Expression left;
    private Expression right;

    public ExpressionMultiply(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form)){
            double leftEvaluated = Double.parseDouble(this.left.evaluate(form).toString());
            double rightEvaluated = Double.parseDouble(this.right.evaluate(form).toString());
            return leftEvaluated * rightEvaluated;
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isNumber(form) && this.right.isNumber(form) && left.isEvaluable(form) && right.isEvaluable(form);
    }

    @Override
    public boolean isNumber(Form form){
        return true;
    }

    @Override
    public String toString() {
        return left.toString() + " * " + right.toString();
    }
}
