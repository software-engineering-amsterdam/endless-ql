package expression;

import model.Form;

public class ExpressionComparisonEq extends ExpressionComparison {

    public ExpressionComparisonEq(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return this.left.evaluate(form).equals(this.right.evaluate(form));
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isEvaluable(form) && this.right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return this.left.toString() + " == " + this.right.toString();
    }
}
