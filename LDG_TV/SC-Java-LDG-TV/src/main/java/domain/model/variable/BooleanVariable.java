package domain.model.variable;

import domain.model.visitor.Visitor;
import javafx.scene.Node;

public class BooleanVariable extends Variable {

    private final Boolean value;

    public BooleanVariable(String text, Boolean value) {
        super(text);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + ": boolean";
    }
    @Override
    public Node getRelatedGUIElement(Visitor v){
        return v.visit(this);
    }
}
