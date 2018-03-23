package gui.widgets;

import gui.model.GUIInterface;
import javafx.beans.InvalidationListener;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

public interface GUIWidget extends GUIInterface {
    Expression getExpressionValue();

    void setValue(Value value);

    // Generic function to give us the ability to set listener
    // of different kinds of JavaFX UI elements (setting changeListener of a checkbox
    // is different than setting changeListener of textbox)
//    void setChangeListener(InvalidationListener invalidationListener);

    // StyleAttribute
    void setColor(String color);

    void setFont(String font);

    void setFontSize(int fontSize);

    void setWidth(int width);

    String getIdentifier();
}
