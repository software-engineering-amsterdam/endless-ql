package domain.model.variable;

import domain.model.stylesheet.UIElement;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.BooleanExpressionValue;
import domain.visitor.Visitor;
import io.reactivex.functions.Consumer;
import javafx.scene.Node;


public abstract class Variable<T> implements Consumer<T> {

    public abstract String getIdentifier();

    public abstract T getComputedValue();

    public abstract UIElement getUiElement();

    public abstract Node getRelatedUIElement(Visitor v);

    public void setValue(ArithmeticExpressionValue value) {
    }

    public void setValue(BooleanExpressionValue value) {
    }

    public void setUiElement(UIElement uiElement) {
    }
}
