package expression;

import model.Form;

public abstract class Expression<T> {

    public abstract boolean isEvaluable(Form form);

    public abstract ReturnType getReturnType(Form form);

    public abstract T evaluate(Form form);

    public boolean isSetable(Form form){
        return false;
    }
    public void setValue(String value){

    }

    public abstract Double divide(Form form, Expression other);
    public abstract Double multiply(Form form, Expression other);
    public abstract Double subtract(Form form, Expression other);
    public abstract Double sum(Form form, Expression other);
    public abstract Boolean equals(Form form, Expression other);
    public abstract Boolean ge(Form form, Expression other);
    public abstract Boolean gt(Form form, Expression other);
    public abstract Boolean le(Form form, Expression other);
    public abstract Boolean lt(Form form, Expression other);
}