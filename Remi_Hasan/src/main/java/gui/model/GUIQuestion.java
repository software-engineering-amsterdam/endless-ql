package gui.model;

import gui.components.LabelWithWidget;
import gui.components.widgets.GUIWidget;
import gui.render.GUIController;
import gui.render.WidgetFactory;
import javafx.scene.control.Label;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class GUIQuestion implements IRenderable {
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

    public Expression getCondition() {
        return condition;
    }

    public Expression getComputedAnswer() {
        return computedAnswer;
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

        // Notify controller if non-computed field is updated by the user
        if (!this.isComputed()) {
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
