package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableUndefined;

public interface GUIWidget<T> {
    // TODO: generic?
    Expression getExpressionValue();

    void setValue(Value value);

    Node getNode();

    void setChangeListener(InvalidationListener invalidationListener);

    void setVisibility(boolean visible);
    void setDisable(boolean disable);
    boolean isDisabled();
    BooleanProperty visibleProperty();

    // StyleAttribute
    void setColor(String color);
    void setFont(String font);
    void setFontSize(int fontSize);
    void setWidth(int width);
}
