package ql.analysis;

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
        // Map label to corresponding questions
        Map<String, Set<String>> questionsPerLabel = this.getQuestionsPerLabel();

        Set<String> warnings = new HashSet<>();
        for(Map.Entry<String, Set<String>> entry : questionsPerLabel.entrySet()) {
            if(entry.getValue().size() == 1) {
                continue;
            }

            warnings.add("Label \"" + entry.getKey() + "\" used for multiple questions: " + entry.getValue());
        }

        return warnings;
    }

    private Map<String, Set<String>> getQuestionsPerLabel() {
        Map<String, Set<String>> questionsPerLabel = new HashMap<>();

        for(Question question : form.questions) {
            Set<String> questionIdentifiers = new HashSet<>();
            if(questionsPerLabel.containsKey(question.label)) {
                questionIdentifiers = questionsPerLabel.get(question.label);
            }

            // Save question identifiers with their location, for reporting purposes
            questionIdentifiers.add(question.identifier + " " + question.getLocation());
            questionsPerLabel.put(question.label, questionIdentifiers);
        }

        return questionsPerLabel;
    }
}
