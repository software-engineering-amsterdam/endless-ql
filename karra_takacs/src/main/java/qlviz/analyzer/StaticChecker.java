package qlviz.analyzer;

import java.util.ArrayList;
import java.util.List;

import qlviz.interpreter.TypedQuestionWalker;
import qlviz.model.Form;

public class StaticChecker {

    public List<AnalysisResult> checkForDuplicateLabels(Form unlinkedForm) {
        DuplicateLabelChecker duplicateLabelChecker = new DuplicateLabelChecker();
        duplicateLabelChecker.initialize(unlinkedForm);
        return duplicateLabelChecker.analyze();
    }


    public List<AnalysisResult> validate(Form form) {
        List<AnalysisResult> staticCheckResults = new ArrayList<>();
        DuplicateQuestionChecker duplicateQuestionChecker = new DuplicateQuestionChecker();
        CircularReferenceChecker circularReferenceChecker = new CircularReferenceChecker();
        DuplicateLabelChecker duplicateLabelChecker = new DuplicateLabelChecker();
        ExpressionTypeChecker expressionTypeChecker = new ExpressionTypeChecker(
                new TypedQuestionWalker()
        );

        duplicateQuestionChecker.initialize(form);
        duplicateLabelChecker.initialize(form);
        circularReferenceChecker.initialize(form);
        expressionTypeChecker.initialize(form);

        staticCheckResults.addAll(duplicateQuestionChecker.analyze());
        staticCheckResults.addAll(duplicateLabelChecker.analyze());
        staticCheckResults.addAll(expressionTypeChecker.analyze());
        staticCheckResults.addAll(circularReferenceChecker.analyze());

        return staticCheckResults;
    }
}
