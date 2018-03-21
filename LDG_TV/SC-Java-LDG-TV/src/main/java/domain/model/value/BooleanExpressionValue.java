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
                return this.leftHandOperand.getValueObject().getValue().equals(this.rightHandOperand.getValueObject().getValue());
            case "!=":
                return this.leftHandOperand.getValueObject().getValue() !=  this.rightHandOperand.getValueObject().getValue();
            case ">":
                return (Integer) this.leftHandOperand.getValueObject().getValue() >  (Integer) this.rightHandOperand.getValueObject().getValue();
            case "<":
                return (Integer) this.leftHandOperand.getValueObject().getValue() <  (Integer) this.rightHandOperand.getValueObject().getValue();
            case ">=":
                return (Integer) this.leftHandOperand.getValueObject().getValue() >= (Integer) this.rightHandOperand.getValueObject().getValue();
            case "<=":
                return (Integer) this.leftHandOperand.getValueObject().getValue() <= (Integer) this.rightHandOperand.getValueObject().getValue();
            default:
                System.out.println("No match found");
        }
        return false;
    }
}
