package expression;

import model.Form;

public class ExpressionString extends Expression<String> {

    private String value;

    public ExpressionString(String value) {
        this.value = value;
    }

    @Override
    public String evaluate(Form form) {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public void setValue(String value){
        this.value = value;
    }

    @Override
    public boolean isEvaluable(Form form){
        return this.value != null;
    }

    @Override
    public boolean isString(Form form){
        return true;
    }
}
