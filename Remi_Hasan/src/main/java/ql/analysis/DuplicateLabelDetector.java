package ql.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.Question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateLabelDetector {

    private final Form form;

    public DuplicateLabelDetector(Form form) {
        this.form = form;
    }

    public Set<String> getDuplicateLabelWarnings() {
        Map<String, Set<String>> questionsPerLabel = this.getQuestionsPerLabel();

        // Find and add warning for all labels that are used for more than one question
        Set<String> warnings = new HashSet<>();
        for(Map.Entry<String, Set<String>> entry : questionsPerLabel.entrySet()) {
            if(entry.getValue().size() > 1) {
                warnings.add("Label '" + entry.getKey() + "' used for multiple questions: " + entry.getValue());
            }
        }

        return warnings;
    }

    // Get a map from label to all question with that label
    private Map<String, Set<String>> getQuestionsPerLabel() {
        Map<String, Set<String>> questionsPerLabel = new HashMap<>();

        this.form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                // New set or existing set
                Set<String> questionIdentifiers = new HashSet<>();
                if(questionsPerLabel.containsKey(question.label)) {
                    questionIdentifiers = questionsPerLabel.get(question.label);
                }

                // Save question identifiers with their location, for reporting purposes
                questionIdentifiers.add(question.identifier + " " + question.getLocation());
                questionsPerLabel.put(question.label, questionIdentifiers);
                return super.visit(question);
            }
        });

        return questionsPerLabel;
    }
}
