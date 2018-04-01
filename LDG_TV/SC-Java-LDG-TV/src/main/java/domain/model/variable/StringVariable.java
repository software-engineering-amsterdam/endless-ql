package domain.model.variable;

import domain.model.stylesheet.UIElement;
import domain.model.value.StringValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class StringVariable extends Variable {
    private Value<String> value;
    private UIElement uiElement;
    private String identifier;

    public StringVariable(String identifier, String value) {
        this.identifier = identifier;
        this.value = new StringValue(value);
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public String getComputedValue() {
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
    public Node getRelatedUIElement(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Object o) {
        this.value.setValue((String) o);
    }
}
