package domain.model.variable;

import domain.model.stylesheet.UIElement;
import domain.model.value.*;
import domain.visitor.Visitor;
import javafx.scene.Node;

import io.reactivex.functions.Consumer;


public abstract class Variable<T> implements Consumer<T>{
    private final String identifier;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
    public abstract T getComputedValue();
    public abstract Value getValueObject();

    public void setValue(ArithmeticExpressionValue value){};
    public void setValue(BooleanExpressionValue value){ };

    public abstract void setUiElement(UIElement uiElement);

    public abstract UIElement getUiElement();

    public abstract Node getRelatedUIElement(Visitor v);


}
