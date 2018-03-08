package domain.model.variable;

import domain.model.value.BooleanValue;
import domain.model.value.ExpressionValue;
import domain.model.value.PlainValue;
import domain.model.value.Value;
import domain.model.visitor.Visitor;
import javafx.scene.Node;


public abstract class Variable {
    private final String identifier;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
    public Value getValue(){ return null;};

    public void setValue(PlainValue value){};
    public void setValue(BooleanValue value){};
    public void setValue(ExpressionValue value){};

    public abstract Node getRelatedUIElement(Visitor v);

}
