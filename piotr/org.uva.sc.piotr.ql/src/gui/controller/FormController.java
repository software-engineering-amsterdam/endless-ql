package gui.controller;

import ast.model.expressions.Expression;
import ast.model.expressions.values.VariableReference;
import gui.model.FormQuestion;
import logic.type.MixedValue;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.ExpressionEvaluator;

import java.util.*;

public class FormController {

    private final List<FormQuestion> formQuestions;
    private final HashMap<String, List<FormQuestion>> dependentFormQuestionsForVisibility = new HashMap<>();
    private final HashMap<String, List<FormQuestion>> dependentFormQuestionsForValue = new HashMap<>();
    private final ExpressionEvaluator evaluator;

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

        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();

        for (FormQuestion formQuestion : formQuestions) {

            // extract dependentFormQuestionsForVisibility
            if (formQuestion.getVisibilityCondition() != null) {

                collectReferencesVisitor.reset();
                formQuestion.getVisibilityCondition().accept(collectReferencesVisitor);

                List<VariableReference> variableReferences = collectReferencesVisitor.getVariableReferences();

                for (VariableReference variableReference : variableReferences) {
                    FormQuestion foundQuestion
                            = this.findFormQuestionByVariableReference(variableReference);
                    if (foundQuestion != null) {
                        List<FormQuestion> dependentQuestions = this.dependentFormQuestionsForVisibility.get(foundQuestion.getVariableName());
                        if (dependentQuestions != null && !dependentQuestions.contains(formQuestion)) {
                            dependentQuestions.add(formQuestion);
                        } else {
                            FormQuestion[] array = new FormQuestion[]{formQuestion};
                            this.dependentFormQuestionsForVisibility.put(foundQuestion.getVariableName(), new ArrayList<>(Arrays.asList(array)));
                        }
                    }
                }

            }

            // TODO: refactor
            // extract dependentFormQuestionsForValue
            if (formQuestion.getAssignedExpression() != null) {

                collectReferencesVisitor.reset();
                formQuestion.getAssignedExpression().accept(collectReferencesVisitor);

                List<VariableReference> variableReferences = collectReferencesVisitor.getVariableReferences();

                for (VariableReference variableReference : variableReferences) {
                    FormQuestion foundQuestion
                            = this.findFormQuestionByVariableReference(variableReference);
                    if (foundQuestion != null) {
                        List<FormQuestion> dependentQuestions = this.dependentFormQuestionsForValue.get(foundQuestion.getVariableName());
                        if (dependentQuestions != null && !dependentQuestions.contains(formQuestion)) {
                            dependentQuestions.add(formQuestion);
                        } else {
                            FormQuestion[] array = new FormQuestion[]{formQuestion};
                            this.dependentFormQuestionsForValue.put(foundQuestion.getVariableName(), new ArrayList<>(Arrays.asList(array)));
                        }
                    }
                }
            }
        }
    }

    private FormQuestion findFormQuestionByVariableReference(VariableReference variableReference) {

        for (FormQuestion formQuestion : this.formQuestions) {
            if (formQuestion.getVariableName().equals(variableReference.getName())) {
                return formQuestion;
            }
        }
        return null;
    }

    public void processFormQuestionChange(FormQuestion formQuestion) {

        List<FormQuestion> formQuestionsVisibility = dependentFormQuestionsForVisibility.get(formQuestion.getVariableName());
        if (formQuestionsVisibility != null) {
            for (FormQuestion formQuestion1 : formQuestionsVisibility) {
                // TODO: check setValue vs changing the value only
                formQuestion1.setVisibility(formQuestion1.getVisibilityCondition().accept(evaluator).getBooleanValue());
                formQuestion1.getPanel().refreshVisibility();
            }
        }

        List<FormQuestion> formQuestionsValue = dependentFormQuestionsForValue.get(formQuestion.getVariableName());
        if (formQuestionsValue != null) {
            for (FormQuestion formQuestion1 : formQuestionsValue) {
                // TODO: check setValue vs changing the value only
                formQuestion1.setValue(formQuestion1.getAssignedExpression().accept(evaluator));
                formQuestion1.getPanel().refreshValue();
            }
        }
    }

    public LinkedHashMap<String, String> prepareResults() {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (FormQuestion formQuestion : this.formQuestions) {
            if (formQuestion.getVisibility()) {
                result.put(formQuestion.getVariableName(), formQuestion.getValue().toString());
            }
        }
        return result;
    }

}
