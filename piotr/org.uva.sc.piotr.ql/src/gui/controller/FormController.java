package gui.controller;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import gui.model.FormQuestionHolder;
import logic.collectors.CollectReferencesVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormController {

    private List<FormQuestionHolder> formQuestionHolders;

    private HashMap<String, List<FormQuestionHolder>> visibilityDependingFormQuestionsPerQuestion = new HashMap<>();
    private HashMap<String, List<FormQuestionHolder>> valueDependingFormQuestionsPerQuestion = new HashMap<>();

    // list of FormQuestions which visibility is depending  this
    // list of FormQuestions which value is depending pn this

    public FormController(List<FormQuestionHolder> formQuestionHolders) throws Exception {

        this.formQuestionHolders = formQuestionHolders;

        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {
            formQuestionHolder.registerController(this);
        }

        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();

        // extract visibilityDependingFormQuestionsPerQuestion
        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {

            if (formQuestionHolder.getVisibilityCondition() != null) {
                formQuestionHolder.getVisibilityCondition().accept(collectReferencesVisitor);

                List<VariableReference> variableReferences = collectReferencesVisitor.getVariableReferences();
                List<FormQuestionHolder> formQuestionHoldersReferences = new ArrayList<>();

                for (VariableReference variableReference : variableReferences) {
                    FormQuestionHolder found = this.findFormQuestionHolderByVariableReference(variableReference);
                    if (found != null) {
                        formQuestionHoldersReferences.add(found);
                    }
                }

                if (formQuestionHoldersReferences.size() > 0) {
                    this.visibilityDependingFormQuestionsPerQuestion.put(formQuestionHolder.getVariableName(), formQuestionHoldersReferences);
                }
            }
        }

        // extract valueDependingFormQuestionsPerQuestion
        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {

            if (formQuestionHolder.getAssignedExpression() != null) {
                formQuestionHolder.getAssignedExpression().accept(collectReferencesVisitor);

                List<VariableReference> variableReferences = collectReferencesVisitor.getVariableReferences();
                List<FormQuestionHolder> formQuestionHoldersReferences = new ArrayList<>();

                for (VariableReference variableReference : variableReferences) {
                    FormQuestionHolder found = this.findFormQuestionHolderByVariableReference(variableReference);
                    if (found != null) {
                        formQuestionHoldersReferences.add(found);
                    }
                }

                if (formQuestionHoldersReferences.size() > 0) {
                    this.valueDependingFormQuestionsPerQuestion.put(formQuestionHolder.getVariableName(), formQuestionHoldersReferences);
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

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(formQuestionHolder.getVariableName() + " has registered a change");

        System.out.println("Hey! The change is going to affect visibility of the following questions: ");
        List<FormQuestionHolder> formQuestionHoldersVisibility = visibilityDependingFormQuestionsPerQuestion.get(formQuestionHolder.getVariableName());
        if (formQuestionHoldersVisibility != null) {
            for (FormQuestionHolder fqh : formQuestionHoldersVisibility) {
                System.out.println("Visibility update for: " + fqh.getVariableName());
            }
        }

        System.out.println("Hey! The change is going to affect value of the following questions: ");
        List<FormQuestionHolder> formQuestionHoldersValue = valueDependingFormQuestionsPerQuestion.get(formQuestionHolder.getVariableName());
        if (formQuestionHoldersValue != null) {
            for (FormQuestionHolder fqh : formQuestionHoldersValue) {
                System.out.println("Value update for: " + fqh.getVariableName());
            }
        }

        // if something has changes, then all relying FormQuestions should be informed
        // thus:
        // - all form questions whose visibility relies on value of this form question
        // - all form questions that have an assigned expression that is holding a reference to this question

        // foreach form question
        // evaluate values
        // evaluate visibility values


    }

}
