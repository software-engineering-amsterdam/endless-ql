package expression;

import model.Form;

public class ExpressionAnd extends Expression<Boolean> {
    private final Expression left;
    private final Expression right;

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

    @Override
    public String toString() {
        return left.toString() + " && " + right.toString();
    }
}