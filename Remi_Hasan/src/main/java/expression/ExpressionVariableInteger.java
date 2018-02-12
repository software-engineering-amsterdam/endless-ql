package expression;

import model.Form;

public class ExpressionVariableInteger extends ExpressionVariable<Integer> {

    public ExpressionVariableInteger(Integer value){
        this.value = value;
    }

    @Override
    public boolean isNumber(Form form){
        return true;
    }

    @Override
    public void setValue(String answer) {
        this.value = Integer.parseInt(answer);
    }
}
