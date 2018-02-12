package expression;

import model.Form;

public class ExpressionComparisonLE extends ExpressionComparison {

    public ExpressionComparisonLE(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            double leftEvaluated = Double.parseDouble(this.left.evaluate(form).toString());
            double rightEvaluated = Double.parseDouble(this.right.evaluate(form).toString());
            return leftEvaluated <= rightEvaluated;
        }
        return null;
    }

    @Override
    public String toString() {
        return left.toString() + " <= " + right.toString();
    }
}