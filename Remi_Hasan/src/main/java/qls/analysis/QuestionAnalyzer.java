package qls.analysis;

import ql.analysis.IdentifiersCollector;
import ql.model.Form;
import qls.QLSVisitor;
import qls.model.QuestionReference;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionAnalyzer implements IQLSAnalysis {

    @Override
    public void analyze(Form form, StyleSheet styleSheet) {
        this.detectDuplicateQuestions(styleSheet);
        this.detectUnknownQuestions(form, styleSheet);
        this.detectUnplacedQuestions(form, styleSheet);
    }

    // Checks whether any question is placed multiple times by the StyleSheet
    private void detectDuplicateQuestions(StyleSheet styleSheet) {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers(styleSheet);
        Set<String> duplicates = this.getDuplicates(styleSheetQuestions);

        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Question(s) placed more than once: " + duplicates);
        }
    }

    // Checks whether any identifiers in QLS file are not in QL file
    private void detectUnknownQuestions(Form form, StyleSheet styleSheet) {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers(styleSheet);
        Set<String> formQuestions = IdentifiersCollector.collectQuestionIdentifiers(form);
        styleSheetQuestions.removeAll(formQuestions);

        if (!styleSheetQuestions.isEmpty()) {
            throw new IllegalArgumentException("Unknown reference(s) to questions: " + styleSheetQuestions);
        }
    }

    // Checks whether any question in QL file is not placed by QLS file
    private void detectUnplacedQuestions(Form form, StyleSheet styleSheet) {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers(styleSheet);
        Set<String> formQuestions = IdentifiersCollector.collectQuestionIdentifiers(form);
        formQuestions.removeAll(styleSheetQuestions);

        if (!formQuestions.isEmpty()) {
            throw new IllegalArgumentException("Unplaced questions by QLS: " + formQuestions);
        }
    }

    private List<String> getStyleSheetQuestionIdentifiers(StyleSheet styleSheet) {
        List<String> identifiers = new ArrayList<>();
        styleSheet.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(QuestionReference questionReference) {
                identifiers.add(questionReference.name);
                return super.visit(questionReference);
            }
        });

        return identifiers;
    }

    // From: https://stackoverflow.com/a/7414753
    private Set<String> getDuplicates(List<String> list) {
        final Set<String> values = new HashSet<>();
        final Set<String> duplicates = new HashSet<>();

        for (String item : list) {
            if (!values.add(item)) {
                duplicates.add(item);
            }
        }

        return duplicates;
    }
}
