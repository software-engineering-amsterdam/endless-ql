package gui.model;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.SymbolTable;
import ql.model.expression.Expression;
import qls.model.StyleSheet;

public class GUILabelWithWidget extends VBox implements GUIInterface {

    public final String identifier;
    private final boolean computed;
    private final Expression condition;
    private final GUIInterface label;
    private final GUIWidget widget;

    public GUILabelWithWidget(String identifier, boolean computed, Expression condition, GUILabel label, GUIWidget widget) {
        this.identifier = identifier;
        this.computed = computed;
        this.condition = condition;
        this.label = label;
        this.widget = widget;

        this.getChildren().add(label.render());
        this.getChildren().add(widget.render());

        this.widget.setDisable(computed);

        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void update(SymbolTable symbolTable) {
        this.widget.update(symbolTable);

        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
        boolean visible = expressionEvaluator.visit(this.condition).getBooleanValue();
        System.out.println("visible[" + identifier + "][" + visible + "]");
        this.setVisible(visible);
    }

    @Override
    public void update(StyleSheet styleSheet) {
        this.widget.update(styleSheet);
    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.widget.setChangeListener(invalidationListener);
    }
}
