package qls.analysis;

import ql.model.Form;
import qls.QLSVisitor;
import qls.model.Question;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionAnalyzer {

    public void detectDuplicateQuestions(StyleSheet styleSheet) {
        List<String> identifiers = this.getStyleSheetQuestionIdentifiers(styleSheet);
        Set<String> duplicates = this.getDuplicates(identifiers);

        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Question(s) referenced more than once: " + duplicates);
        }
    }

    // Checks whether any identifiers in QLS file are not in QL file
    public void detectUnknownQuestions(Form form, StyleSheet styleSheet) {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers(styleSheet);
        List<String> formQuestions = this.getFormQuestionIdentifiers(form);
        styleSheetQuestions.removeAll(formQuestions);

        if (!styleSheetQuestions.isEmpty()) {
            throw new IllegalArgumentException("Unknown reference(s) to questions: " + styleSheetQuestions);
        }
    }

    // Checks whether any question in QL file is not placed by QLS file
    public void detectUnplacedQuestions(Form form, StyleSheet styleSheet) {
        List<String> styleSheetQuestions = this.getStyleSheetQuestionIdentifiers(styleSheet);
        List<String> formQuestions = this.getFormQuestionIdentifiers(form);
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

    private List<String> getFormQuestionIdentifiers(Form form) {
        List<String> identifiers = new ArrayList<>();

        for (ql.model.Question question : form.questions) {
            identifiers.add(question.name);
        }

        return identifiers;
    }

    private List<String> getStyleSheetQuestionIdentifiers(StyleSheet styleSheet) {
        List<String> identifiers = new ArrayList<>();
        styleSheet.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                identifiers.add(question.name);
                return super.visit(question);
            }
        });

        return identifiers;
    }
}
