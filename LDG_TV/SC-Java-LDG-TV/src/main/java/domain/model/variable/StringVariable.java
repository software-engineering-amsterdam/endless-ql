package domain.model.variable;

import domain.model.value.PlainValue;
import domain.model.value.Value;
import domain.model.visitor.Visitor;
import javafx.scene.Node;

public class StringVariable extends Variable {
    private Value value;

    public StringVariable(String identifier) {
        super(identifier);
    }

    @Override
    public Value getValue() {
        return value;
    }
    @Override
    public void setValue(PlainValue value){
        this.value = value;
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
