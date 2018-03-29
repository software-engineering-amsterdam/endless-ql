package gui.elements;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

// Interface, so we can for example create an ILabelWithWidget based on VBox or based on HBox
// and talk to the interface
public interface ILabelWithWidget {
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
