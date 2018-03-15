package domain.model.variable;

import domain.model.value.BooleanExpressionValue;
import domain.model.value.BooleanValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class BooleanVariable extends Variable {

    private Value<Boolean> value;

    public BooleanVariable(String identifier) {
        super(identifier);
        this.value = new BooleanValue(false); // TODO move setting of value upstream (pass in consturctor)
    }

    @Override
    public Value getValue() {
        return value;
    }

    public void setValue(Boolean newValue){
        this.value.setValue(newValue);
    } //TODO set back to BooleanValue type.

    @Override
    public void setValue(BooleanExpressionValue value){this.value = value;};

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
