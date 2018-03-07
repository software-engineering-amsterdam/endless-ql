package domain.model.variable;

import domain.model.visitor.Visitor;
import javafx.scene.Node;

public class StringVariable extends Variable {
    private final String value;

    public StringVariable(String name, String value) {
        super(name);
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + ": String = " + this.value;
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
