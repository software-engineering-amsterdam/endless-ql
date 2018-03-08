package domain.model.variable;

import domain.model.value.PlainValue;
import domain.model.visitor.Visitor;
import javafx.scene.Node;

public class BooleanVariable extends Variable {

    private Boolean value;

    public BooleanVariable(String identifier) {
        super(identifier);
    }

    public Boolean getValue() {
        return value;
    }
    @Override
    public void setValue(PlainValue value){
        this.value = Boolean.valueOf(value.getValue());
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
