package domain.model.expression;

import domain.model.variable.Value;
import domain.model.variable.Variable;
import domain.model.visitor.Visitor;
import javafx.scene.Node;


public class Expression extends Value{

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public Expression(Variable leftHandOperand, Variable rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }


}
