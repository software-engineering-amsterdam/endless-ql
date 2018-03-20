package domain.model.variable;

import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.MoneyValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class MoneyVariable extends Variable {
    private Value value;

    public MoneyVariable(String identifier) {
        super(identifier);
        value = new MoneyValue(0); // TODO move setting of value upstream (pass in consturctor)
    }
    @Override
    public Value getValue() {
        return value;
    }
    @Override
    public void setValue(MoneyValue value){
        this.value = value;
    }
    @Override
    public void setValue(ArithmeticExpressionValue e){
        this.value = e;
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
