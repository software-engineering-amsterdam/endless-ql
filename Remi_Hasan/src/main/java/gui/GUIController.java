package gui;

import gui.elements.LabelWithWidget;
import gui.model.GUIQuestion;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GUIController {
    private final SymbolTable symbolTable;
    private Map<GUIQuestion, LabelWithWidget> guiQuestionWidgets = new HashMap<>();

    public GUIController(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void register(GUIQuestion guiQuestion, LabelWithWidget labelWithWidget) {
        guiQuestionWidgets.put(guiQuestion, labelWithWidget);
    }

    public void update(GUIQuestion guiQuestion, Expression expression) {
        symbolTable.setExpression(guiQuestion.getIdentifier(), expression);
        this.updateQuestionWidgets();
    }

    public void updateQuestionWidgets() {
        this.updateDisplayedQuestions();
        this.updateComputedQuestions();
    }


    private void updateDisplayedQuestions() {
        // Update visibility and symbol table value for every question
        Set<String> visibleQuestions = new HashSet<>();

        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : this.guiQuestionWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget guiWidget = mapEntry.getValue();

            boolean visible = guiQuestion.isVisible(this.symbolTable);
            guiWidget.setVisibility(visible);

            // Update symbol table, as another question with the same identifier could now be visible
            if(visible) {
                if(guiQuestion.isComputed()) {
                    this.symbolTable.setExpression(guiQuestion.getIdentifier(), guiQuestion.getComputedAnswer());
                } else {
                    this.symbolTable.setExpression(guiQuestion.getIdentifier(), guiWidget.getExpressionValue());
                }

                visibleQuestions.add(guiQuestion.getIdentifier());
            } else if(!visibleQuestions.contains(guiQuestion.getIdentifier())) {
                // If question becomes invisible, set value in symbol table to undefined
                // but only if another question with the same identifier that is visible
                // did not update the symbol table already, which we keep track of using visibleQuestions
                this.symbolTable.setExpression(guiQuestion.getIdentifier(), new ExpressionVariableUndefined(guiQuestion.getType()));
            }
        }
    }

    private void updateComputedQuestions() {
        // Update all rendered values of the computed questions
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this.symbolTable);
        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : this.guiQuestionWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget guiWidget = mapEntry.getValue();

            if(guiQuestion.isComputed()) {
                Value result = expressionEvaluator.visit(guiQuestion.getComputedAnswer());
                guiWidget.setValue(result);
            }
        }
    }
}
