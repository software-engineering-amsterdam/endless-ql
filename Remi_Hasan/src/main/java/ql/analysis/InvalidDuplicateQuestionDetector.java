package ql.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.ReturnType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvalidDuplicateQuestionDetector extends QLBaseVisitor<Void> {
    private final Form form;

    public InvalidDuplicateQuestionDetector(Form form) {
        this.form = form;
    }

    public void detect() {
        Map<String, ReturnType> questionTypes = new HashMap<>();
        Set<String> invalidQuestions = new HashSet<>();

        this.form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                // If already saw identifier with different type, add this one to the invalid questions
                if (questionTypes.containsKey(question.identifier) && questionTypes.get(question.identifier) != question.type) {
                    invalidQuestions.add(question.identifier + " " + question.getLocation());
                } else {
                    questionTypes.put(question.identifier, question.type);
                }
                return super.visit(question);
            }
        });

        if (!invalidQuestions.isEmpty()) {
            throw new IllegalArgumentException("Redeclaration of question(s) with different type: " + invalidQuestions);
        }
    }
}
