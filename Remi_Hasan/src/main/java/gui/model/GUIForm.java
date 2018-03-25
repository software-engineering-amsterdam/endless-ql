package gui.model;

import gui.elements.LabelWithWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.*;

public class GUIForm extends VBox {
    public final String identifier;
    private final List<GUIQuestion> guiQuestions;

    public GUIForm(String identifier, List<GUIQuestion> guiQuestions) {
        this.identifier = identifier;
        this.guiQuestions = guiQuestions;
    }

    public Parent render(SymbolTable symbolTable) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        // Render all questions, save as map so a rendered question can be updated using the corresponding GUIQuestion
        Map<GUIQuestion, LabelWithWidget> guiWidgetsMap = this.getRenderedQuestions(symbolTable);

        // Add questions to rendered form
        vBox.getChildren().addAll(guiWidgetsMap.values());

        // Update question values/visibility for the first time
        this.updateRenderedQuestions(guiWidgetsMap, symbolTable);

        // Wrap form in scroll pane, so questions will always be reachable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    private Map<GUIQuestion, LabelWithWidget> getRenderedQuestions(SymbolTable symbolTable) {
        // LinkedHashMap, as for QL we want to render the questions in order
        Map<GUIQuestion, LabelWithWidget> guiWidgetsMap = new LinkedHashMap<>();

        // Listener that is notified by UI widget input event
        InvalidationListener allWidgetsListener = observable -> {
            this.updateRenderedQuestions(guiWidgetsMap, symbolTable);
        };

        // Render all QL questions in order
        for (GUIQuestion guiQuestion : this.guiQuestions) {
            LabelWithWidget labelWithWidget = guiQuestion.render(symbolTable, allWidgetsListener);
            guiWidgetsMap.put(guiQuestion, labelWithWidget);
        }

        return guiWidgetsMap;
    }

    void updateRenderedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
        // First show/hide questions and update symbol table
        // Do this before updating calculated values, so we are not relying on the order of the questions
        this.updateDisplayedQuestions(guiWidgets, symbolTable);

        // Then update the calculated question widget values using symbol table
        this.updateComputedQuestions(guiWidgets, symbolTable);
    }

    private void updateDisplayedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
        // Update visibility and symbol table value for every question
        Set<String> visibleQuestions = new HashSet<>();

        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : guiWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget guiWidget = mapEntry.getValue();

            boolean visible = guiQuestion.isVisible(symbolTable);
            guiWidget.setVisibility(visible);

            // Update symbol table, as another question with the same identifier could now be visible
            if(visible) {
                if(guiQuestion.isComputed()) {
                    symbolTable.setExpression(guiQuestion.identifier, guiQuestion.computedAnswer);
                } else {
                    symbolTable.setExpression(guiQuestion.identifier, guiWidget.getExpressionValue());
                }

                visibleQuestions.add(guiQuestion.identifier);
            } else if(!visibleQuestions.contains(guiQuestion.identifier)) {
                // If question becomes invisible, set value in symbol table to undefined
                // but only if another question with the same identifier that is visible
                // did not update the symbol table already, which we keep track of using visibleQuestions
                symbolTable.setExpression(guiQuestion.identifier, new ExpressionVariableUndefined(null, guiQuestion.type));
            }
        }
    }

    private void updateComputedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
        // Update all rendered values of the computed questions
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : guiWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget guiWidget = mapEntry.getValue();

            if(guiQuestion.isComputed()) {
                Value result = expressionEvaluator.visit(guiQuestion.computedAnswer);
                guiWidget.setValue(result);
            }
        }
    }
}