package gui.model;

import gui.elements.LabelWithWidget;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import ql.evaluation.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class GUIQuestion implements IGUIQuestion {
    // TODO: private
    private final String identifier;
    public final String label;
    public final ReturnType type;
    public final Expression condition;
    public final Expression computedAnswer;

    public GUIQuestion(String identifier, String label, ReturnType type, Expression condition, Expression computedAnswer) {
        this.identifier = identifier;
        this.label = label;
        this.type = type;
        this.condition = condition;
        this.computedAnswer = computedAnswer;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
        return expressionEvaluator.visit(this.condition).getBooleanValue();
    }

    public boolean isComputed() {
        return this.computedAnswer != null;
    }

    public LabelWithWidget render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.type);
        return this.render(guiWidget, symbolTable, allWidgetsListener);
    }

    LabelWithWidget render(GUIWidget guiWidget, SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        Label guiLabel = new Label(this.label);

        // Update symbol table and other fields in UI if non-computed field is edited by user
        if(!this.isComputed()) {
            guiWidget.setChangeListener(observable -> {
                symbolTable.setExpression(this.identifier, guiWidget.getExpressionValue());

                // Notify GUIForm that an input value has changed, so it can update all fields
                allWidgetsListener.invalidated(observable);
            });
        }

        LabelWithWidget labelWithWidget = new LabelWithWidget(guiLabel, guiWidget);
        labelWithWidget.setDisable(this.isComputed());

        // TODO: temporary hack, improve this
        GUIFormWithStyling.guiWidgetsMap.put(this, labelWithWidget);

        return labelWithWidget;
    }
}
