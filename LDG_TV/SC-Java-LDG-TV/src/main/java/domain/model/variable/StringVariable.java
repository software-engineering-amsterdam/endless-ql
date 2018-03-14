package domain.model.variable;

import domain.model.value.PlainValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class StringVariable extends Variable {
    private Value<String> value;

    public StringVariable(String identifier) {
        super(identifier);
        this.value = new PlainValue("");
    }

    @Override
    public Value getValue() {
        return value;
    }

    public void setValue(String newVale){
        value.setValue(newVale);
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
