package ql.analysis.warning;

import ql.QLBaseVisitor;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import ql.model.statement.Question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateLabelDetector implements IQLWarningAnalysis {

    @Override
    public Set<String> analyze(Form form, SymbolTable symbolTable) {
        Map<String, Set<String>> questionsPerLabel = this.getQuestionsPerLabel(form);

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
    private Map<String, Set<String>> getQuestionsPerLabel(Form form) {
        Map<String, Set<String>> questionsPerLabel = new HashMap<>();

        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                // New set or existing set
                Set<String> questionIdentifiers = new HashSet<>();
                if(questionsPerLabel.containsKey(question.getLabel())) {
                    questionIdentifiers = questionsPerLabel.get(question.getLabel());
                }

                // Save question identifiers with their location, for reporting purposes
                questionIdentifiers.add(question.getIdentifier() + " " + question.getLocation());
                questionsPerLabel.put(question.getLabel(), questionIdentifiers);
                return super.visit(question);
            }
        });

        return questionsPerLabel;
    }
}
