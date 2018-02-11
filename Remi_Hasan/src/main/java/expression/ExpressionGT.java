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
        System.out.println("GT: " + isEvaluable(form));
        System.out.println("GT: " + this.left.isNumber(form));
        System.out.println("GT: " + this.right.isNumber(form));
        if(isEvaluable(form)){
            return Double.parseDouble(this.left.evaluate(form).toString()) > Double.parseDouble(this.right.evaluate(form).toString());
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
