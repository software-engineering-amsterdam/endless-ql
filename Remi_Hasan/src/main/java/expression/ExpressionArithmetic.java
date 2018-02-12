package expression;

import model.Form;

public abstract class ExpressionArithmetic extends Expression<Double> {

    Expression left;
    Expression right;

    @Override
    public boolean isNumber(Form form) {
        return true;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isEvaluable(form) && this.right.isEvaluable(form) && this.left.isNumber(form) && this.right.isNumber(form);
    }

    public String toString(String op){
        return this.left.toString() + op + this.right.toString();
    }
}
