package domain.model.variable;

import domain.model.expression.Expression;
import domain.model.visitor.Visitor;
import javafx.scene.Node;

public class MoneyVariable extends Variable {
    private Value value;

    public MoneyVariable(String name, Value value) {
        super(name);
        this.value = value;
    }
    public Value getValue() {
        return value;
    }
    @Override
    public void setValue(PlainValue value){
        this.value = value;
    }
    @Override
    public void setValue(Expression e){
        this.value = e;
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
