package domain.model.question;

import domain.model.question.Variable;

public class ExpressionVariable extends Variable{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public ExpressionVariable(String name, Variable leftHandOperand, Variable rightHandOperand, String operator){
        super(name);
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }

}
