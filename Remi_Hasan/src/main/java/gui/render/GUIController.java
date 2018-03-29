package gui.render;

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
    private final Map<GUIQuestion, LabelWithWidget> guiQuestionWidgets = new HashMap<>();

    public GUIController(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    // Keep track of all rendered questions and their corresponding GUI widgets
    public void register(GUIQuestion guiQuestion, LabelWithWidget labelWithWidget) {
        guiQuestionWidgets.put(guiQuestion, labelWithWidget);
    }

    // Handle a changed question field
    public void update(GUIQuestion guiQuestion, Expression expression) {
        symbolTable.setExpression(guiQuestion.getIdentifier(), expression);
        this.updateQuestionWidgets();
    }

    public void updateQuestionWidgets() {
        this.updateDisplayedQuestions();
        this.updateComputedQuestions();
    }

    // Update visibility and symbol table value for every question
    private void updateDisplayedQuestions() {
        Set<String> visibleQuestions = new HashSet<>();

        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : this.guiQuestionWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget labelWithWidget = mapEntry.getValue();

            boolean visible = guiQuestion.isVisible(this.symbolTable);
            labelWithWidget.setVisibility(visible);

            // Update symbol table, as another question with the same identifier could now be visible
            if(visible) {
                if(guiQuestion.isComputed()) {
                    this.symbolTable.setExpression(guiQuestion.getIdentifier(), guiQuestion.getComputedAnswer());
                } else {
                    this.symbolTable.setExpression(guiQuestion.getIdentifier(), labelWithWidget.getExpressionValue());
                }

                visibleQuestions.add(guiQuestion.getIdentifier());
            } else if(!visibleQuestions.contains(guiQuestion.getIdentifier())) {
                // If question becomes invisible, set value in symbol table to undefined
                // but only if another question with the same identifier was made visible already,
                // which we keep track of using visibleQuestions
                this.symbolTable.setExpression(guiQuestion.getIdentifier(),
                        new ExpressionVariableUndefined(guiQuestion.getType()));
            }
        }
    }

    // Update all rendered values of the computed questions
    private void updateComputedQuestions() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this.symbolTable);
        for (Map.Entry<GUIQuestion, LabelWithWidget> mapEntry : this.guiQuestionWidgets.entrySet()) {
            GUIQuestion guiQuestion = mapEntry.getKey();
            LabelWithWidget labelWithWidget = mapEntry.getValue();

            if(guiQuestion.isComputed()) {
                Value result = expressionEvaluator.visit(guiQuestion.getComputedAnswer());
                labelWithWidget.setValue(result);
            }
        }
    }
}
