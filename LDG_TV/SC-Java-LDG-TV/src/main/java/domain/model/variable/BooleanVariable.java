package domain.model.variable;

import domain.model.value.BooleanExpressionValue;
import domain.model.value.BooleanValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class BooleanVariable extends Variable {

    private Value<Boolean> value;

    public BooleanVariable(String identifier, boolean value) {
        super(identifier);
        this.value = new BooleanValue(value); // TODO move setting of value upstream (pass in consturctor)
    }

    @Override
    public Value<Boolean> getValueObject() {
        return value;
    }

    @Override
    public void setValue(BooleanExpressionValue value){this.value = value;};

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
