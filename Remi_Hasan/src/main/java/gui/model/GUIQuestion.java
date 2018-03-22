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

    public GUIWidget guiWidget;

    public GUIQuestion(String identifier, String label, ReturnType type, Expression condition, boolean computed) {
        this.identifier = identifier;
        this.label = label;
        this.type = type;
        this.condition = condition;
        this.computed = computed;
    }

    public Parent render(SymbolTable symbolTable, InvalidationListener invalidationListener) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);

        VBox vBox = new VBox();

        Label guiLabel = new Label(this.label);
        this.guiWidget = WidgetFactory.getDefaultWidget(this.type);

        // Disable field if it is computed as it can not be edited
        this.guiWidget.setDisable(this.computed);

        // Event handling
        boolean visible = expressionEvaluator.visit(this.condition).getBooleanValue();
        this.guiWidget.setVisibility(visible);

        if(!this.computed) {
            this.guiWidget.setChangeListener(observable -> {
                symbolTable.setExpression(this.identifier, this.guiWidget.getExpressionValue());

                // Notify GUIForm that an input value has changed, so it can update all fields
                invalidationListener.invalidated(observable);
            });
        }

        // Bind label visibility to widget visibility
        guiLabel.visibleProperty().bind(guiWidget.visibleProperty());
        guiLabel.managedProperty().bind(guiLabel.visibleProperty());

        vBox.getChildren().add(guiLabel);
        vBox.getChildren().add(guiWidget.getNode());
        vBox.setPadding(new Insets(0, 0, 0, 20));

        return vBox;
    }
}
