package domain.model.variable;

import domain.model.stylesheet.UIElement;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.MoneyValue;
import domain.model.value.Value;
import domain.visitor.Visitor;
import javafx.scene.Node;

public class MoneyVariable extends Variable {
    private Value<Integer> value;
    private UIElement uiElement;
    private String identifier;

    public MoneyVariable(String identifier, int value) {
        this.identifier = identifier;
        this.value = new MoneyValue(value);
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Integer getComputedValue() {
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
    public void setValue(ArithmeticExpressionValue e) {
        this.value = e;
    }

    @Override
    public Node getRelatedUIElement(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Object o) {
        this.value.setValue((Integer) o);
    }
}
