package domain.model.value;

import domain.model.variable.Variable;


public class ExpressionValue extends Value{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public ExpressionValue(Variable leftHandOperand, Variable rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }


}
