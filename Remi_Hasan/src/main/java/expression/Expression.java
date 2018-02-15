package expression;

import model.Form;

public abstract class Expression<T> {

    public abstract ReturnType getReturnType(Form form);

    public abstract ExpressionVariable evaluate(Form form);

    public boolean isSetable(Form form){
        return false;
    }
    public void setValue(String value){

    }
}