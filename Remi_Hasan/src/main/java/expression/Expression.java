package expression;

import model.Form;

public abstract class Expression<T> {

    public abstract T evaluate(Form form);

    public abstract boolean isEvaluable(Form form);

    public boolean isNumber(Form form){
        return false;
    }

    public boolean isString(Form form){
        return false;
    }

    public boolean isBoolean(Form form){
        return false;
    }

    public void setValue(String value){}

}
