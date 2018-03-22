package gui.model;

import gui.widgets.*;
import gui.widgets.CheckboxWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

import java.util.List;

public class GUIQuestion {
    public final String identifier;
    public final String label;
    public final ReturnType type;
    public final Expression condition;
    public final boolean computed;

    public GUIQuestion(String identifier, String label, ReturnType type, Expression condition, boolean computed) {
        this.identifier = identifier;
        this.label = label;
        this.type = type;
        this.condition = condition;
        this.computed = computed;
    }

    public LabelWithWidget render(SymbolTable symbolTable, InvalidationListener invalidationListener) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);

        Label guiLabel = new Label(this.label);
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.type);

        // Event handling
        if(!this.computed) {
            guiWidget.setChangeListener(observable -> {
                symbolTable.setExpression(this.identifier, guiWidget.getExpressionValue());

                // Notify GUIForm that an input value has changed, so it can update all fields
                invalidationListener.invalidated(observable);
            });
        }

        LabelWithWidget labelWithWidget = new LabelWithWidget(guiLabel, guiWidget);

        // Show/hide field based on condition
        boolean visible = expressionEvaluator.visit(this.condition).getBooleanValue();
        labelWithWidget.setVisible(visible);

        // Disable field if it is computed as it can not be edited
        labelWithWidget.setDisable(this.computed);

        return labelWithWidget;
    }
}
