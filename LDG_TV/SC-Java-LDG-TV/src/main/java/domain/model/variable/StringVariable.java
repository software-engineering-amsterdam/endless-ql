package domain.model.variable;

import domain.model.value.StringValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class StringVariable extends Variable {
    private Value<String> value;

    public StringVariable(String identifier, String value) {
        super(identifier);
        this.value = new StringValue(value);
    }

    @Override
    public Value<String> getValue() {
        return value;
    }
    @Override
    public void setValue(StringValue value){
        this.value = value;
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
