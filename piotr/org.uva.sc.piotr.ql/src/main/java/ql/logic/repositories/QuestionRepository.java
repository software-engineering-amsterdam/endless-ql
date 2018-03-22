package ql.logic.repositories;

import ql.ast.model.statements.Question;

import java.util.List;

public final class QuestionRepository {

    public static Question findQuestionByVariableName(String variableName, List<Question> questions) {
        for (Question question : questions) {
            if (question.getVariableName().equals(variableName)) {
                return question;
            }
        }
        return null;
    }

}
