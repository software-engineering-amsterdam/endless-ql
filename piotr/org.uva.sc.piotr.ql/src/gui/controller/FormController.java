package gui.controller;

import ast.model.expressions.values.VariableReference;
import gui.model.FormQuestion;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.ExpressionEvaluator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FormController {

    private final List<FormQuestion> formQuestions;
    private final ExpressionEvaluator evaluator;
    private final CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();

    public FormController(List<FormQuestion> formQuestions, ExpressionEvaluator evaluator) throws Exception {
        this.evaluator = evaluator;

        // initial evaluation
        for (FormQuestion formQuestion : formQuestions) {
            if (formQuestion.getAssignedExpression() != null) {
                formQuestion.setValue(formQuestion.getAssignedExpression().accept(evaluator));
            }
            if (formQuestion.getVisibilityCondition() != null) {
                formQuestion.setVisibility(formQuestion.getVisibilityCondition().accept(evaluator).getBooleanValue());
            } else {
                formQuestion.setVisibility(true);
            }
        }

        this.formQuestions = formQuestions;

        for (FormQuestion formQuestion : formQuestions) {
            formQuestion.registerController(this);
        }
    }

    private List<FormQuestion> extractFormQuestionsWithAssignedExpressionDirectlyReferencingTo(FormQuestion formQuestion) {

        List<FormQuestion> result = new ArrayList<>();

        for (FormQuestion formQuestion1 : this.formQuestions) {
            if (formQuestion1.getAssignedExpression() != null) {

                List<VariableReference> variableReferences = this.collectReferencesVisitor.getVariableReferences(formQuestion1.getAssignedExpression());

                for (VariableReference variableReference : variableReferences) {
                    if (variableReference.getName().equals(formQuestion.getVariableName())) {
                        result.add(formQuestion1);
                    }
                }
            }
        }

        return result;
    }

    private List<FormQuestion> extractFormQuestionsWithVisibilityDirectlyReferencingTo(FormQuestion formQuestion) {

        List<FormQuestion> result = new ArrayList<>();

        for (FormQuestion formQuestion1 : this.formQuestions) {
            if (formQuestion1.getVisibilityCondition() != null) {
                List<VariableReference> variableReferences = this.collectReferencesVisitor.getVariableReferences(formQuestion1.getVisibilityCondition());
                for (VariableReference variableReference : variableReferences) {
                    if (variableReference.getName().equals(formQuestion.getVariableName())) {
                        result.add(formQuestion1);
                    }
                }
            }
        }

        return result;
    }

    public void processFormQuestionChange(FormQuestion formQuestion) {

        List<FormQuestion> formQuestionsToUpdateValue = this.extractFormQuestionsWithAssignedExpressionDirectlyReferencingTo(formQuestion);
        if (!formQuestionsToUpdateValue.isEmpty()) {
            for (FormQuestion formQuestion1 : formQuestionsToUpdateValue) {
                // they must (per definition) have assigned expression
                formQuestion1.setValue(formQuestion1.getAssignedExpression().accept(evaluator));
                formQuestion1.getPanel().refreshValue();
                this.processFormQuestionChange(formQuestion1);
            }
        }

        List<FormQuestion> formQuestionsToUpdateVisibility = this.extractFormQuestionsWithVisibilityDirectlyReferencingTo(formQuestion);
        if (!formQuestionsToUpdateVisibility.isEmpty()) {
            for (FormQuestion formQuestion1 : formQuestionsToUpdateVisibility) {
                // they must (per definition) have visibility conditions
                formQuestion1.setVisibility(formQuestion1.getVisibilityCondition().accept(evaluator).getBooleanValue());
                formQuestion1.getPanel().refreshVisibility();
                this.processFormQuestionChange(formQuestion1);
            }
        }
    }

    public LinkedHashMap<String, Object> prepareResults() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        for (FormQuestion formQuestion : this.formQuestions) {
            if (formQuestion.getVisibility()) {
                switch (formQuestion.getValue().getType()) {
                    case INTEGER:
                        result.put(formQuestion.getVariableName(), formQuestion.getValue().getIntegerValue());
                        break;
                    case DECIMAL:
                        result.put(formQuestion.getVariableName(), formQuestion.getValue().getDecimalValue());
                        break;
                    case BOOLEAN:
                        result.put(formQuestion.getVariableName(), formQuestion.getValue().getBooleanValue());
                        break;
                    case STRING:
                        result.put(formQuestion.getVariableName(), formQuestion.getValue().getStringValue());
                        break;
                    default:
                        result.put(formQuestion.getVariableName(), formQuestion.getValue().toString());
                        break;
                }
            }
        }
        return result;
    }

}
