package gui.model;

import gui.widgets.GUIWidget;
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

        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);

        Map<String, List<GUIWidget>> guiWidgets = new HashMap<>();
        Map<GUIWidget, Expression> guiWidgetConditions = new HashMap<>();

        for (GUIQuestion guiQuestion : this.guiQuestions) {
            Label label = new Label(guiQuestion.label);
            Parent guiWidget = guiQuestion.render(symbolTable);

            // Disable field if it is computed as it can not be edited
            guiWidget.setDisable(guiQuestion.computed);

            // Display question based on condition
            boolean visible = expressionEvaluator.visit(guiQuestion.condition).getBooleanValue();
//            guiWidget.setVisibility(visible);
//            guiWidgetConditions.put(guiWidget, guiQuestion.condition);
//
//            // If question is not computed, listen for and handle changed value
//            if (!guiQuestion.computed) {
//                guiWidget.setChangeListener(observable -> {
//                    symbolTable.setExpression(guiQuestion.identifier, guiWidget.getExpressionValue());
//                    this.updateRenderedQuestions(guiWidgets, guiWidgetConditions, expressionEvaluator, symbolTable);
//                });
//            }
//
//            // Bind label visibility to widget visibility
//            label.visibleProperty().bind(guiWidget.visibleProperty());
//            label.managedProperty().bind(label.visibleProperty());
//
//            // Add widget to map from identifier to corresponding UI elements
//            List<GUIWidget> mapEntry = new ArrayList<>();
//            if(guiWidgets.containsKey(guiQuestion.identifier)) {
//                mapEntry = guiWidgets.get(guiQuestion.identifier);
//            }
//            mapEntry.add(guiWidget);
//            guiWidgets.put(guiQuestion.identifier, mapEntry);
//
//            // Render elements
//            vBox.getChildren().add(label);
            vBox.getChildren().add(guiWidget);

        }
        vBox.setPadding(new Insets(100, 10, 10, 10));

        return vBox;
    }

    private void updateRenderedQuestions(Map<String, List<GUIWidget>> guiWidgets,
                                         Map<GUIWidget, Expression> guiWidgetConditions,
                                         ExpressionEvaluator expressionEvaluator, SymbolTable symbolTable) {
        for(GUIQuestion guiQuestion : guiQuestions) {
            List<GUIWidget> guiWidgetsEntries = guiWidgets.get(guiQuestion.identifier);
            for (GUIWidget guiWidget : guiWidgetsEntries) {
                // Toggle visibility by evaluating the widget's condition
                Expression condition = guiWidgetConditions.get(guiWidget);
                guiWidget.setVisibility(expressionEvaluator.visit(condition).getBooleanValue());

                // Disabled, so it is a computed field that should be updated
                if (guiWidget.isDisabled()) {
                    Value result = expressionEvaluator.visit(symbolTable.getExpression(guiQuestion.identifier));
                    guiWidget.setValue(result);
                }
            }
        }
    }
}
