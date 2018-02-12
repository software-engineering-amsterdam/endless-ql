package expression;

import model.Form;

public class ExpressionBinaryLogicalAnd extends ExpressionBinaryLogical {

    public ExpressionBinaryLogicalAnd(Expression left, Expression right){
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
        return left.isEvaluable(form) && right.isEvaluable(form) && left.isBoolean(form) && right.isBoolean(form);
    }

    public String toString(Form form) {
        return left.toString() + " && " + right.toString();
    }

}