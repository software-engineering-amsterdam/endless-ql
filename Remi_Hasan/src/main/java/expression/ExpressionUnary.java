package expression;

import model.Form;

interface UnaryOperation {
    ExpressionVariable operation(Form form, ExpressionVariable v);
}
public abstract class ExpressionUnary<T> extends Expression<T> {
    final Expression v;
    final UnaryOperation op;
    final String opString;

    public ExpressionUnary(Expression v, UnaryOperation op, String opString){
        this.v = v;
        this.op = op;
        this.opString = opString;
    }

    public abstract ReturnType getReturnType(Form form);

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.op.operation(form, this.v.evaluate(form));
    }

    @Override
    public String toString(){
        return opString + this.v.toString();
    }
}