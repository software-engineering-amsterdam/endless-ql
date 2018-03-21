package domain.model.value;

import domain.model.variable.Variable;

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
        switch (operator) {
            case "==":
                return this.leftHandOperand.getValue().getValue() ==  this.rightHandOperand.getValue().getValue();
            case "!=":
                return this.leftHandOperand.getValue().getValue() !=  this.rightHandOperand.getValue().getValue();
            case ">":
                return (Integer) this.leftHandOperand.getValue().getValue() >  (Integer) this.rightHandOperand.getValue().getValue();
            case "<":
                return (Integer) this.leftHandOperand.getValue().getValue() <  (Integer) this.rightHandOperand.getValue().getValue();
            case ">=":
                return (Integer) this.leftHandOperand.getValue().getValue() >= (Integer) this.rightHandOperand.getValue().getValue();
            case "<=":
                return (Integer) this.leftHandOperand.getValue().getValue() <= (Integer) this.rightHandOperand.getValue().getValue();
        }
        return false;
    }
    public void setValue(Boolean value) {
        // TODO
    }

    @Override
    public void accept(Boolean aBoolean) throws Exception {

    }
}
