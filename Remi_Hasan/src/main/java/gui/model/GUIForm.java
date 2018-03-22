package gui.model;

import gui.elements.LabelWithWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.*;

public class GUIForm extends VBox {
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
        InvalidationListener allWidgetsListener = observable -> {
            this.updateRenderedQuestions(guiWidgetsMap, symbolTable);
        };

        // Render all QL questions in order
        for (GUIQuestion guiQuestion : this.guiQuestions) {
            LabelWithWidget labelWithWidget = guiQuestion.render(symbolTable, allWidgetsListener);
            vBox.getChildren().add(labelWithWidget);

            // Add widget to map from identifier to corresponding UI elements
            guiWidgetsMap.put(guiQuestion, labelWithWidget);
        }

        // Update question values/visibility for the first time
        this.updateRenderedQuestions(guiWidgetsMap, symbolTable);

        vBox.setPadding(new Insets(10, 10, 10, 10));

        // Wrap form in scroll pane, so questions will always be reachable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    private void updateRenderedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
        // First show/hide questions and update symbol table
        // Do this before updating calculated values, so we are not relying on the order of the questions
        this.updateDisplayedQuestions(guiWidgets, symbolTable);

        // Then update the calculated question widget values using symbol table
        this.updateCalculatedQuestions(guiWidgets, symbolTable);
    }

    private void updateDisplayedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
        // Keep track of which questions are rendered
        // so we only set an invisible question in the symbol table
        // to Undefined when it was not updated by another visible question yet
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
                // did not update the symbol table already
                symbolTable.setExpression(guiQuestion.identifier, new ExpressionVariableUndefined(null, guiQuestion.type));
            }
        }
    }

    private void updateCalculatedQuestions(Map<GUIQuestion, LabelWithWidget> guiWidgets, SymbolTable symbolTable) {
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