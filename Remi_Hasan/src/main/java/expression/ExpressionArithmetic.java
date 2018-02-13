package expression;

import model.Form;

interface ArithmeticOperation {
    Double operation(Form form, Expression a, Expression b);
}

public abstract class ExpressionArithmetic extends Expression<Double> {

    final Expression left;
    final Expression right;
    final ArithmeticOperation op;
    final String opString;

    ExpressionArithmetic(Expression left, Expression right, ArithmeticOperation op, String opString){
        this.left = left;
        this.right = right;
        this.op = op;
        this.opString = opString;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isEvaluable(form)
                && this.right.isEvaluable(form)
                && this.left.getReturnType(form) == ReturnType.Number
                && this.right.getReturnType(form) == ReturnType.Number;
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form)){
            return this.op.operation(form, this.left, this.right);
        }
        return null;
    }

    // TODO
    @Override
    public Double divide(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.divide(form, other);
    }

    @Override
    public Double multiply(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.multiply(form, other);
    }

    @Override
    public Double subtract(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.subtract(form, other);
    }

    @Override
    public Double sum(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.sum(form, other);
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.equals(form, other);
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.ge(form, other);
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.gt(form, other);
    }

    @Override
    public Boolean le(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.le(form, other);
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        ExpressionVariableDecimal thisEvaluated = new ExpressionVariableDecimal(this.evaluate(form));
        return thisEvaluated.lt(form, other);
    }

    @Override
    public String toString(){
        return this.left.toString() + opString + this.right.toString();
    }
}