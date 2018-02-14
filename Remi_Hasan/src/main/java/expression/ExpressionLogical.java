package expression;

import model.Form;

interface LogicalOperation {
    boolean operation(Form form, Expression a, Expression b);
}

public abstract class ExpressionLogical extends Expression<Boolean> {
    final Expression left;
    final Expression right;
    final LogicalOperation op;
    final String opString;

    ExpressionLogical(Expression left, Expression right, LogicalOperation op, String opString){
        this.left = left;
        this.right = right;
        this.op = op;
        this.opString = opString;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.left.isEvaluable(form)
                && this.right.isEvaluable(form)
                && this.left.getReturnType(form) == ReturnType.Boolean
                && this.right.getReturnType(form) == ReturnType.Boolean;
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Boolean;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            return this.op.operation(form, this.left, this.right);
        }
        return null;
    }

    @Override
    public Double divide(Form form, Expression other) {
        return null;
    }

    @Override
    public Double multiply(Form form, Expression other) {
        return null;
    }

    @Override
    public Double subtract(Form form, Expression other) {
        return null;
    }

    @Override
    public Double sum(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        return this.evaluate(form).equals(other.evaluate(form));
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean le(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        return null;
    }

    @Override
    public String toString() {
        return left.toString() + opString + right.toString();
    }
}