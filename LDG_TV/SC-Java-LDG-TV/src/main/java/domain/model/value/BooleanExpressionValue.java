package domain.model.value;

import domain.model.variable.Variable;
import exception.InvalidArithmaticExpressionException;

public class BooleanExpressionValue extends Value<Boolean>{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public BooleanExpressionValue(Variable leftHandOperand, Variable rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }
    @Override
    public Boolean getValue() {
        try {
            switch (operator) {
                case ">":
                    return (Integer) this.leftHandOperand.getValue().getValue() >  (Integer) this.rightHandOperand.getValue().getValue();
                case "==":
                    return (Integer) this.leftHandOperand.getValue().getValue() ==  (Integer) this.rightHandOperand.getValue().getValue();
                case "!=":
                    return (Integer) this.leftHandOperand.getValue().getValue() !=  (Integer) this.rightHandOperand.getValue().getValue();
                case "<":
                    return (Integer) this.leftHandOperand.getValue().getValue() <  (Integer) this.rightHandOperand.getValue().getValue();
                case ">=":
                    return (Integer) this.leftHandOperand.getValue().getValue() >= (Integer) this.rightHandOperand.getValue().getValue();
                case "<=":
                    return (Integer) this.leftHandOperand.getValue().getValue() <= (Integer) this.rightHandOperand.getValue().getValue();
            }
        }catch(InvalidArithmaticExpressionException e){
            throw new InvalidArithmaticExpressionException("Invalid aritmatic expression found. Are you using strings were numbers are expected?");
        }
        return false;
    }

    @Override
    public void setValue(Boolean value) {
        // TODO
    }
}
