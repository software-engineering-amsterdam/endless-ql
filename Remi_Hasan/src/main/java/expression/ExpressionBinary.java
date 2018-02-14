package expression;

import model.Form;

interface BinaryOperation {
    ExpressionVariable operation(Form form, ExpressionVariable a, ExpressionVariable b);
}

public abstract class ExpressionBinary<T> extends Expression<T> {
    final Expression left;
    final Expression right;
    final BinaryOperation op;
    final String opString;

    public ExpressionBinary(Expression left, Expression right, BinaryOperation op, String opString){
        this.left = left;
        this.right = right;
        this.op = op;
        this.opString = opString;
    }

    public abstract ReturnType getReturnType(Form form);

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.op.operation(form, this.left.evaluate(form), this.right.evaluate(form));
    }

    @Override
    public String toString(){
        return this.left.toString() + opString + this.right.toString();
    }
}