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
    public String toString(){
        return leftHandOperand.getName() + " " + operator + " " + rightHandOperand.getName();
    }
    @Override
    public Node getRelatedUIElement(Visitor v) {
        return v.visit(this);
    }
}
