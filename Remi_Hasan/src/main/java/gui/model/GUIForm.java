package gui.model;

import gui.widgets.GUIWidget;
import gui.widgets.LabelWithWidget;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIForm extends VBox{
    public final String identifier;
    public final List<GUIQuestion> guiQuestions;

    public GUIForm(String identifier, List<GUIQuestion> guiQuestions) {
        this.identifier = identifier;
        this.guiQuestions = guiQuestions;
    }

    public Parent render(SymbolTable symbolTable) {
        VBox vBox = new VBox();

        Map<GUIQuestion, LabelWithWidget> guiWidgetsMap = new HashMap<>();

        // Listener that is notified by UI widget input event
        InvalidationListener invalidationListener = observable -> {
            this.updateRenderedQuestions(guiWidgetsMap, symbolTable);
        };

        for (GUIQuestion guiQuestion : this.guiQuestions) {
            LabelWithWidget labelWithWidget = guiQuestion.render(symbolTable, invalidationListener);
            vBox.getChildren().add(labelWithWidget);

            // Add widget to map from identifier to corresponding UI elements
            guiWidgetsMap.put(guiQuestion, labelWithWidget);
        }

        vBox.setPadding(new Insets(100, 10, 10, 10));

        return vBox;
    }

    private void updateRenderedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);

        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : guiWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget guiWidget = mapEntry.getValue();

            // Toggle visibility by evaluating the widget's condition
            guiWidget.setVisibility(expressionEvaluator.visit(guiQuestion.condition).getBooleanValue());

            // Disabled, so it is a computed field that should be updated
            if (guiWidget.isDisabled()) {
                Value result = expressionEvaluator.visit(symbolTable.getExpression(guiQuestion.identifier));
                guiWidget.setValue(result);
            }
        }
    }
}