package expression;

import model.Form;

public abstract class ExpressionVariable<T> extends Expression<T>{

    T value;

    @Override
    public boolean isEvaluable(Form form){
        return this.value != null;
    }

    @Override
    public T evaluate(Form form) {
        return value;
    }

    @Override
    public boolean isSetable(Form form){
        return true;
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        return this.evaluate(form).equals(other.evaluate(form));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
