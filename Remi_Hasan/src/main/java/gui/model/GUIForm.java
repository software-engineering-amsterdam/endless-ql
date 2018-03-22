package gui.model;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIForm {
    public final String identifier;
    public final List<GUIQuestion> guiQuestions;

    public GUIForm(String identifier, List<GUIQuestion> guiQuestions) {
        this.identifier = identifier;
        this.guiQuestions = guiQuestions;
    }

    public Parent render(SymbolTable symbolTable) {
        VBox vBox = new VBox();

        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);

        Map<String, List<GUIWidget>> guiWidgets = new HashMap<>();

        for (GUIQuestion guiQuestion : this.guiQuestions) {
            Label label = new Label(guiQuestion.label);
            GUIWidget guiWidget = guiQuestion.render();
            guiWidget.setDisable(guiQuestion.computed);

            // Show/hide based on condition
            guiWidget.setVisibility(expressionEvaluator.visit(guiQuestion.condition).getBooleanValue());

            if (!guiQuestion.computed) {
                guiWidget.setChangeListener(observable -> {
                    // Update value in symbol table
                    symbolTable.setExpression(guiQuestion.identifier, guiWidget.getExpressionValue());

                    // Update all gui widgets
                    for(GUIQuestion guiQuestion1 : guiQuestions) {
                        List<GUIWidget> guiWidgetsEntries = guiWidgets.get(guiQuestion1.identifier);
                        for (GUIWidget guiWidgetEntry : guiWidgetsEntries) {
                            // Toggle visibility by evaluating the question's condition
                            guiWidgetEntry.setVisibility(expressionEvaluator.visit(guiQuestion1.condition).getBooleanValue());

                            // Disabled, so it is a computed field that should be updated
                            if (guiWidgetEntry.isDisabled()) {
                                Value result = expressionEvaluator.visit(symbolTable.getExpression(guiQuestion1.identifier));
                                guiWidgetEntry.setValue(result);
                            }
                        }
                    }
                });
            }

            // Bind label visibility to widget visibility
            label.visibleProperty().bind(guiWidget.visibleProperty());
            label.managedProperty().bind(label.visibleProperty());

            // Add widget to map from identifier to corresponding UI elements
            List<GUIWidget> mapEntry = new ArrayList<>();
            if(guiWidgets.containsKey(guiQuestion.identifier)) {
                mapEntry = guiWidgets.get(guiQuestion.identifier);
            }
            mapEntry.add(guiWidget);
            guiWidgets.put(guiQuestion.identifier, mapEntry);

            // Render elements
            vBox.getChildren().add(label);
            vBox.getChildren().add(guiWidget.getNode());
        }

        return vBox;
    }
}
