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
    private String identifier;

    public BooleanVariable(String identifier, boolean value) {
        this.identifier = identifier;
        this.value = new BooleanValue(value);
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Boolean getComputedValue() {
        return value.getValue();
    }

    @Override
    public UIElement getUiElement() {
        return uiElement;
    }

    @Override
    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    @Override
    public void setValue(BooleanExpressionValue value) {
        this.value = value;
    }

    @Override
    public Node getRelatedUIElement(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Object o) {
        this.value.setValue((Boolean) o);
    }
}
