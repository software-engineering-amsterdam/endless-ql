package expression;

import model.Form;

public class ExpressionVariableDate extends ExpressionVariable<String> {
    // TODO: figure out best way to save and/or validate a date

    public ExpressionVariableDate(String value) {
        this.value = value;
    }

    @Override
    public void setValue(String value){
        this.value = value;
    }

    @Override
    public boolean isSetable(Form form){
        return true;
    }
}
