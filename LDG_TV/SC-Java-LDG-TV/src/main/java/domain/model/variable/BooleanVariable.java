package domain.model.variable;

import domain.model.stylesheet.UIElement;
import domain.model.value.BooleanExpressionValue;
import domain.model.value.BooleanValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class BooleanVariable extends Variable {

    private Value<Boolean> value;
    private UIElement uiElement;
    public BooleanVariable(String identifier, boolean value) {
        super(identifier);
        this.value = new BooleanValue(value);
    }

    @Override
    public Value<Boolean> getValueObject() {
        return value;
    }

    @Override
    public void setValue(BooleanValue value){this.value = value;}

    @Override
    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    @Override
    public UIElement getUiElement() {
        return uiElement;
    }

    ;

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
