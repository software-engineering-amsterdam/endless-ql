package ql.analysis.error;

import ql.QLBaseVisitor;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.ReturnType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvalidDuplicateQuestionDetector implements IQLErrorAnalysis {

    @Override
    public void analyze(Form form, SymbolTable symbolTable) {
        Map<String, ReturnType> questionTypes = new HashMap<>();
        Set<String> invalidQuestions = new HashSet<>();

        // Visit all questions and check if there are questions with the same identifier but different types
        form.accept(new QLBaseVisitor<Void>() {
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
