package expression;

import model.Form;

public class ExpressionLT extends Expression<Boolean>{

    private final Expression left;
    private final Expression right;

    public ExpressionLT(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return (double)this.left.evaluate(form) < (double)this.right.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isEvaluable(form) && this.right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return left.toString() + " < " + right.toString();
    }
}