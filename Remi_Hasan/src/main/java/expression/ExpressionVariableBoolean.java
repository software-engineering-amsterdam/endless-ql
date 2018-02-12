package expression;

import model.Form;

public class ExpressionVariableBoolean extends ExpressionVariable<Boolean> {

    public ExpressionVariableBoolean(Boolean value){
        this.value = value;
    }

    @Override
    public boolean isBoolean(Form form){
        return true;
    }

    @Override
    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }
}
