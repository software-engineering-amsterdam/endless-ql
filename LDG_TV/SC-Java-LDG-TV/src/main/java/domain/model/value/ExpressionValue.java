package domain.model.value;

import domain.model.variable.Variable;
import exception.InvalidAritmaticExpressionException;

public class ExpressionValue extends Value<Integer>{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public ExpressionValue(Variable leftHandOperand, Variable rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }
    @Override
    public Integer getValue() {
        try {
            switch (operator) {
                case "/":
                    return Integer.valueOf((String) this.leftHandOperand.getValue().getValue()) / Integer.valueOf((String) this.rightHandOperand.getValue().getValue());
                case "+":
                    return Integer.valueOf((String) this.leftHandOperand.getValue().getValue()) + Integer.valueOf((String) this.rightHandOperand.getValue().getValue());
                case "-":
                    return Integer.valueOf((String) this.leftHandOperand.getValue().getValue()) - Integer.valueOf((String) this.rightHandOperand.getValue().getValue());
                case "*":
                    return Integer.valueOf((String) this.leftHandOperand.getValue().getValue()) * Integer.valueOf((String) this.rightHandOperand.getValue().getValue());
            }
        }catch(InvalidAritmaticExpressionException e){
            throw new InvalidAritmaticExpressionException("Invalid arithmatic expression found. Are you using strings were numbers are expected?");
        }
        return null;
    }
}
