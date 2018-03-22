package gui.model;

import gui.elements.LabelWithWidget;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class GUIQuestion {
    public final String identifier;
    public final String label;
    public final ReturnType type;
    private final Expression condition;
    private final boolean computed;

    public GUIQuestion(String identifier, String label, ReturnType type, Expression condition, boolean computed) {
        this.identifier = identifier;
        this.label = label;
        this.type = type;
        this.condition = condition;
        this.computed = computed;
    }

    public LabelWithWidget render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        Label guiLabel = new Label(this.label);
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.type);

        // Event handling
        if(!this.computed) {
            guiWidget.setChangeListener(observable -> {
                symbolTable.setExpression(this.identifier, guiWidget.getExpressionValue());

                // Notify GUIForm that an input value has changed, so it can update all fields
                allWidgetsListener.invalidated(observable);
            });
        }

        LabelWithWidget labelWithWidget = new LabelWithWidget(guiLabel, guiWidget);

        // Show/hide field based on condition
        labelWithWidget.setVisible(this.isVisible(symbolTable));

        // Disable field if it is computed as it can not be edited
        labelWithWidget.setDisable(this.computed);

        return labelWithWidget;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
        return expressionEvaluator.visit(this.condition).getBooleanValue();
    }

    public boolean isComputed() {
        return computed;
    }
}
