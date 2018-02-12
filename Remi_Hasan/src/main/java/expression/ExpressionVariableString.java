package expression;

import model.Form;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        this.value = value;
    }

    @Override
    public String evaluate(Form form) {
        return value;
    }

    @Override
    public void setValue(String value){
        this.value = value;
    }

    @Override
    public boolean isString(Form form){
        return true;
    }
}
