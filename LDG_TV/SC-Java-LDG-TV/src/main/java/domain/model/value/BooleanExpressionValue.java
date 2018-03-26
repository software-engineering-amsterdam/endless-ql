package domain.model.value;

import domain.model.variable.Variable;

public class BooleanExpressionValue extends BooleanValue{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public BooleanExpressionValue(Variable leftHandOperand, Variable rightHandOperand, String operator){
        super(false);
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }

    @Override
    public Boolean getValue() {
        switch (operator) {
            case "==":
                return leftHandOperand.getComputedValue().equals(rightHandOperand.getComputedValue());
            case "!=":
                return leftHandOperand.getComputedValue() !=  rightHandOperand.getComputedValue();
            case ">":
                return (Integer) leftHandOperand.getComputedValue() >  (Integer) rightHandOperand.getComputedValue();
            case "<":
                return (Integer) leftHandOperand.getComputedValue() <  (Integer) rightHandOperand.getComputedValue();
            case ">=":
                return (Integer) leftHandOperand.getComputedValue() >= (Integer) rightHandOperand.getComputedValue();
            case "<=":
                return (Integer) leftHandOperand.getComputedValue() <= (Integer) rightHandOperand.getComputedValue();
            default:
        }
        return false;
    }
}
