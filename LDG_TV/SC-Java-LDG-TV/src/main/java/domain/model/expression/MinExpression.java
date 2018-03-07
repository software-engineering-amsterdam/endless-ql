package domain.model.expression;

import domain.model.variable.Variable;

public class MinExpression extends Expression {
    private Variable leftHandOperand;
    private Variable rightHandOperand;

    public MinExpression(Variable leftHandOperand, Variable rightHandOperand) {
        super(leftHandOperand, rightHandOperand, "/");
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
    }

}
