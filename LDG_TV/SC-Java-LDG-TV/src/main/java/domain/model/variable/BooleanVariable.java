package domain.model.variable;

import domain.model.visitor.Visitor;
import javafx.scene.Node;

public class BooleanVariable extends Variable {

    private Boolean value;

    public BooleanVariable(String text, Boolean value) {
        super(text);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }
    @Override
    public void setValue(PlainValue value){
        this.value = Boolean.valueOf(value.getValue());
    }

    @Override
    public String toString() {
        return this.getName() + ": boolean = " + this.value;
    }
    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
