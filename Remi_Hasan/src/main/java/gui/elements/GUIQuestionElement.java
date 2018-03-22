package gui.elements;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

public interface GUIQuestionElement {
    Node getNode();

    // Functions to delegate to the internal GUIWidget
    Expression getExpressionValue();
    void setValue(Value value);
    void setChangeListener(InvalidationListener invalidationListener);
    void setVisibility(boolean visible);

    // StyleAttribute
    void setColor(String color);
    void setFont(String font);
    void setFontSize(int fontSize);
    void setWidth(int width);
}
