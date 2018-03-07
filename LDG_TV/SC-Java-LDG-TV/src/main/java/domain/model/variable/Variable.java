package domain.model.variable;

import domain.model.expression.Expression;
import domain.model.visitor.Visitor;
import javafx.scene.Node;


public abstract class Variable {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setValue(PlainValue value){};
    public void setValue(Expression value){};
    public abstract Node getRelatedUIElement(Visitor v);

}
