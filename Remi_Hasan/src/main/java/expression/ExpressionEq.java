package expression;

import model.Form;

public class ExpressionEq extends Expression<Boolean> {
    private Expression left;
    private Expression right;

    public ExpressionEq(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Form form) {
//        Object leftEval = this.left.evaluate(form);
//        Object rightEval = this.right.evaluate(form);
//        if(leftEval == null){
//            return rightEval == null;
//        }
//        return leftEval.equals(rightEval);
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

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}
