package expression;

import model.Form;

public class ExpressionArithmeticDivide extends ExpressionArithmetic {

    public ExpressionArithmeticDivide(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form)){
            double leftEvaluated = Double.parseDouble(this.left.evaluate(form).toString());
            double rightEvaluated = Double.parseDouble(this.right.evaluate(form).toString());
            if(rightEvaluated != 0){
                return leftEvaluated * rightEvaluated;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString("/");
    }
}
