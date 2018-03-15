package domain.model.value;

import domain.model.variable.Variable;
import exception.InvalidArithmeticExpressionException;

public class ArithmeticExpressionValue extends Value<Integer>{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public ArithmeticExpressionValue(Variable leftHandOperand, Variable rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }
    @Override
    public Integer getValue() {
        try {
            switch (operator) {
                case "/":
                    return (Integer) this.leftHandOperand.getValue().getValue() / (Integer) this.rightHandOperand.getValue().getValue();
                case "+":
                    return (Integer) this.leftHandOperand.getValue().getValue() + (Integer) this.rightHandOperand.getValue().getValue();
                case "-":
                    return (Integer) this.leftHandOperand.getValue().getValue() - (Integer) this.rightHandOperand.getValue().getValue();
                case "*":
                    return (Integer) this.leftHandOperand.getValue().getValue() * (Integer) this.rightHandOperand.getValue().getValue();
            }
        }catch(Exception e){
            throw new InvalidArithmeticExpressionException("Invalid aritmatic expression found. Are you using strings were numbers are expected?");
        }
        return null;
    }

    @Override
    public void setValue(Integer value) {
        // TODO
    }

    @Override
    public void accept(Integer integer) throws Exception {

    }
}
