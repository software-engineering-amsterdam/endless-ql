package domain.model.variable;

import domain.model.stylesheet.UIElement;
import domain.model.value.StringValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class StringVariable extends Variable {
    private Value<String> value;
    private UIElement uiElement;

    public StringVariable(String identifier, String value) {
        super(identifier);
        this.value = new StringValue(value);
    }

    @Override
    public Value<String> getValueObject() {
        return value;
    }
    @Override
    public void setValue(StringValue value){
        this.value = value;
    }

    @Override
    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    @Override
    public UIElement getUiElement() {
        return uiElement;
    }

    @Override
    public Node getRelatedUIElement(Visitor v){
        return v.visit(this);
    }
}
