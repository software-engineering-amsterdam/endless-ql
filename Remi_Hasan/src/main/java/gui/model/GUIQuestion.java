package gui.model;

import gui.GUIController;
import gui.elements.LabelWithWidget;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import ql.evaluation.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class GUIQuestion implements IGUIQuestion {
    private final String identifier;
    private final String label;
    private final ReturnType type;
    private final Expression condition;
    private final Expression computedAnswer;

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

    public ReturnType getType() {
        return type;
    }

    public Expression getComputedAnswer() {
        return computedAnswer;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
        return expressionEvaluator.visit(this.condition).getBooleanValue();
    }

    public boolean isComputed() {
        return this.computedAnswer != null;
    }

    public LabelWithWidget render(GUIController guiController) {
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.type);
        return this.render(guiWidget, guiController);
    }

    LabelWithWidget render(GUIWidget guiWidget, GUIController guiController) {
        Label guiLabel = new Label(this.label);

        // Update symbol table and other fields in UI if non-computed field is edited by user
        if(!this.isComputed()) {
            guiWidget.setChangeListener(observable -> {
                guiController.update(this, guiWidget.getExpressionValue());
            });
        }

        LabelWithWidget labelWithWidget = new LabelWithWidget(guiLabel, guiWidget);
        labelWithWidget.setDisable(this.isComputed());

        guiController.register(this, labelWithWidget);

        return labelWithWidget;
    }
}
