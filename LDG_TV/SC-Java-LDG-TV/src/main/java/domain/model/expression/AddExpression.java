package domain.model.expression;

import domain.model.variable.Variable;

public class AddExpression extends Expression {
    private Variable leftHandOperand;
    private Variable rightHandOperand;

    public AddExpression(Variable leftHandOperand, Variable rightHandOperand) {
        super(leftHandOperand, rightHandOperand, "/");
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
    }

}
