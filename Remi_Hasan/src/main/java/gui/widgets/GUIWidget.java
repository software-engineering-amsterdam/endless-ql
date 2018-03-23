package gui.widgets;

import gui.model.GUIInterface;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

public interface GUIWidget extends GUIInterface {
    Expression getExpressionValue();

    void setValue(Value value);

    // StyleAttribute
    void setColor(String color);

    void setFont(String font);

    void setFontSize(int fontSize);

    void setWidth(int width);

    String getIdentifier();
}
