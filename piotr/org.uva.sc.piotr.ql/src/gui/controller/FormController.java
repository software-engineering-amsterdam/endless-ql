package gui.controller;

import ast.model.expressions.Expression;
import ast.model.expressions.values.VariableReference;
import gui.model.FormQuestionHolder;
import gui.model.MixedValueHolder;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.ExpressionEvaluator;

import java.util.*;

public class FormController {

    private List<FormQuestionHolder> formQuestionHolders;
    private HashMap<String, List<FormQuestionHolder>> dependentFormQuestionsForVisibility = new HashMap<>();
    private HashMap<String, List<FormQuestionHolder>> dependentFormQuestionsForValue = new HashMap<>();
    private ExpressionEvaluator evaluator;

    public FormController(List<FormQuestionHolder> formQuestionHolders, ExpressionEvaluator evaluator) throws Exception {

        this.evaluator = evaluator;

        // initial evaluation
        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {
            if (formQuestionHolder.getAssignedExpression() != null) {
                formQuestionHolder.setValueHolder(formQuestionHolder.getAssignedExpression().accept(evaluator));
            }
            if (formQuestionHolder.getVisibilityCondition() != null) {
                formQuestionHolder.setVisibilityHolder(formQuestionHolder.getVisibilityCondition().accept(evaluator));
            } else {
                formQuestionHolder.setVisibilityHolder(new MixedValueHolder(Expression.DataType.BOOLEAN, true));
            }
        }

        this.formQuestionHolders = formQuestionHolders;

        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {
            formQuestionHolder.registerController(this);
        }

        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();

        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {

            // extract dependentFormQuestionsForVisibility
            if (formQuestionHolder.getVisibilityCondition() != null) {

                collectReferencesVisitor.reset();
                formQuestionHolder.getVisibilityCondition().accept(collectReferencesVisitor);

                List<VariableReference> variableReferences = collectReferencesVisitor.getVariableReferences();

                for (VariableReference variableReference : variableReferences) {
                    FormQuestionHolder foundQuestionHolder
                            = this.findFormQuestionHolderByVariableReference(variableReference);
                    if (foundQuestionHolder != null) {
                        List<FormQuestionHolder> dependentQuestionHolders = this.dependentFormQuestionsForVisibility.get(foundQuestionHolder.getVariableName());
                        if (dependentQuestionHolders != null && !dependentQuestionHolders.contains(formQuestionHolder)) {
                            dependentQuestionHolders.add(formQuestionHolder);
                        } else {
                            FormQuestionHolder[] array = new FormQuestionHolder[]{formQuestionHolder};
                            this.dependentFormQuestionsForVisibility.put(foundQuestionHolder.getVariableName(), new ArrayList<>(Arrays.asList(array)));
                        }
                    }
                }

            }

            // TODO: refactor
            // extract dependentFormQuestionsForValue
            if (formQuestionHolder.getAssignedExpression() != null) {

                collectReferencesVisitor.reset();
                formQuestionHolder.getAssignedExpression().accept(collectReferencesVisitor);

                List<VariableReference> variableReferences = collectReferencesVisitor.getVariableReferences();

                for (VariableReference variableReference : variableReferences) {
                    FormQuestionHolder foundQuestionHolder
                            = this.findFormQuestionHolderByVariableReference(variableReference);
                    if (foundQuestionHolder != null) {
                        List<FormQuestionHolder> dependentQuestionHolders = this.dependentFormQuestionsForValue.get(foundQuestionHolder.getVariableName());
                        if (dependentQuestionHolders != null && !dependentQuestionHolders.contains(formQuestionHolder)) {
                            dependentQuestionHolders.add(formQuestionHolder);
                        } else {
                            FormQuestionHolder[] array = new FormQuestionHolder[]{formQuestionHolder};
                            this.dependentFormQuestionsForValue.put(foundQuestionHolder.getVariableName(), new ArrayList<>(Arrays.asList(array)));
                        }
                    }
                }
            }
        }
    }

    private FormQuestionHolder findFormQuestionHolderByVariableReference(VariableReference variableReference) {

        for (FormQuestionHolder formQuestionHolder : this.formQuestionHolders) {
            if (formQuestionHolder.getVariableName().equals(variableReference.getName())) {
                return formQuestionHolder;
            }
        }
        return null;
    }

    public void processFormQuestionHolderChange(FormQuestionHolder formQuestionHolder) {

        List<FormQuestionHolder> formQuestionHoldersVisibility = dependentFormQuestionsForVisibility.get(formQuestionHolder.getVariableName());

        if (formQuestionHoldersVisibility != null) {
            for (FormQuestionHolder fqh : formQuestionHoldersVisibility) {
                // TODO: check setValueHolder vs changing the value only
                fqh.setVisibilityHolder(fqh.getVisibilityCondition().accept(evaluator));
                fqh.getPanel().refreshVisibility();
            }
        }

        List<FormQuestionHolder> formQuestionHoldersValue = dependentFormQuestionsForValue.get(formQuestionHolder.getVariableName());
        if (formQuestionHoldersValue != null) {
            for (FormQuestionHolder fqh : formQuestionHoldersValue) {
                // TODO: check setValueHolder vs changing the value only
                fqh.setValueHolder(fqh.getAssignedExpression().accept(evaluator));
                fqh.getPanel().refreshValue();
            }
        }
    }

    public LinkedHashMap<String, String> prepareResults() {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (FormQuestionHolder formQuestionHolder : this.formQuestionHolders) {
            if (formQuestionHolder.getVisibilityHolder().getBooleanValue()) {
                result.put(formQuestionHolder.getVariableName(), formQuestionHolder.getValueHolder().toString());
            }
        }
        return result;
    }

}
