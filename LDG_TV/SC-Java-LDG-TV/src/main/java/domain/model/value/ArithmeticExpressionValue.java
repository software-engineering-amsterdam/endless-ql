package domain.model.value;

import domain.model.variable.Variable;
import exception.InvalidArithmeticExpressionException;

public class ArithmeticExpressionValue extends MoneyValue{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public ArithmeticExpressionValue(Variable leftHandOperand, Variable rightHandOperand, String operator){
        super(0);
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }
    @Override
    public Integer getValue() {
        try {
            switch (operator) {
                case "/":
                    return (Integer) this.leftHandOperand.getValueObject().getValue() / (Integer) this.rightHandOperand.getValueObject().getValue();
                case "+":
                    return (Integer) this.leftHandOperand.getValueObject().getValue() + (Integer) this.rightHandOperand.getValueObject().getValue();
                case "-":
                    return (Integer) this.leftHandOperand.getValueObject().getValue() - (Integer) this.rightHandOperand.getValueObject().getValue();
                case "*":
                    return (Integer) this.leftHandOperand.getValueObject().getValue() * (Integer) this.rightHandOperand.getValueObject().getValue();
            }
        }catch(Exception e){
            throw new InvalidArithmeticExpressionException("Invalid arithmetic expression found. Are you using strings were numbers are expected?");
        }
        return null;
    }
}
