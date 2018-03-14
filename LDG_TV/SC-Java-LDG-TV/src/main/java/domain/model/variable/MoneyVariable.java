package domain.model.variable;

import domain.model.value.ExpressionValue;
import domain.model.value.PlainValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class MoneyVariable extends Variable {
    private Value value;

    public MoneyVariable(String identifier) {
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
    public void setValue(ExpressionValue e){
        this.value = e;
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
