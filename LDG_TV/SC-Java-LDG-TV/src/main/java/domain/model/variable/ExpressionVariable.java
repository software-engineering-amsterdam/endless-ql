package domain.model.variable;

import domain.model.visitor.Visitor;
import javafx.scene.Node;


public class ExpressionVariable extends Variable {

    private Variable leftHandOperand;
    private Variable rightHandOperand;
    private String operator;

    public ExpressionVariable(String name, Variable leftHandOperand, Variable rightHandOperand, String operator){
        super(name);
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }

    @Override
    public Node getRelatedGUIElement(Visitor v) {
        return null;
    }
}
