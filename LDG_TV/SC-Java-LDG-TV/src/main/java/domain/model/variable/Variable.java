package domain.model.variable;

import domain.model.value.ExpressionValue;
import domain.model.value.PlainValue;
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
    public void setValue(PlainValue value){};
    public void setValue(ExpressionValue value){};
    public abstract Node getRelatedUIElement(Visitor v);

}
