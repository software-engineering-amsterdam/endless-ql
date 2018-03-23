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

public class QuestionAnalyzer {

    private final Form form;
    private final StyleSheet styleSheet;

    public QuestionAnalyzer(Form form, StyleSheet styleSheet) {
        this.form = form;
        this.styleSheet = styleSheet;
    }

    public void detectDuplicateQuestions() {
        List<String> identifiers = this.getStyleSheetQuestionIdentifiers();
        Set<String> duplicates = this.getDuplicates(identifiers);

        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Question(s) referenced more than once: " + duplicates);
        }
    }

    // Checks whether any identifiers in QLS file are not in QL file
    public void detectUnknownQuestions() {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers();
        List<String> formQuestions = IdentifiersCollector.collectQuestionIdentifiers(this.form);
        styleSheetQuestions.removeAll(formQuestions);

        if (!styleSheetQuestions.isEmpty()) {
            throw new IllegalArgumentException("Unknown reference(s) to questions: " + styleSheetQuestions);
        }
    }

    // Checks whether any question in QL file is not placed by QLS file
    public void detectUnplacedQuestions() {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers();
        List<String> formQuestions = IdentifiersCollector.collectQuestionIdentifiers(this.form);
        formQuestions.removeAll(styleSheetQuestions);

        if (!formQuestions.isEmpty()) {
            throw new IllegalArgumentException("Unplaced questions by QLS: " + formQuestions);
        }
    }

    // From: https://stackoverflow.com/a/7414753
    private Set<String> getDuplicates(List<String> list) {
        final Set<String> values = new HashSet<>();
        final Set<String> duplicates = new HashSet<>();

        for (String yourInt : list) {
            if (!values.add(yourInt)) {
                duplicates.add(yourInt);
            }
        }

        return duplicates;
    }

    private List<String> getStyleSheetQuestionIdentifiers() {
        List<String> identifiers = new ArrayList<>();
        this.styleSheet.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(QuestionReference questionReference) {
                identifiers.add(questionReference.name);
                return super.visit(questionReference);
            }
        });

        return identifiers;
    }
}
