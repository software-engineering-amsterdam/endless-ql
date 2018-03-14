package logic.validators;

import ast.model.statements.Question;

import java.util.List;
import java.util.Objects;

public final class QuestionsValidator {

    public static void validateDuplicates(List<Question> questions) throws RuntimeException {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getVariableName(), question2.getVariableName())
                        && !Objects.equals(question1.getVariableType().getIdentifier(), question2.getVariableType().getIdentifier())
                        ) {
                    throw new RuntimeException("Duplicate question declarations with different types in lines : "
                            + question1.getMetaInformation().getStartLine()
                            + "-"
                            + question1.getMetaInformation().getEndLine()
                            + " and "
                            + question2.getMetaInformation().getStartLine()
                            + "-"
                            + question2.getMetaInformation().getEndLine()
                    );
                }
            }
        }
    }

    public static  void validateLabels(List<Question> questions) throws Exception {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getLabel(), question2.getLabel())) {
                    throw new Exception("Duplicate label declarations in lines : "
                            + question1.getMetaInformation().getStartLine()
                            + " and "
                            + question2.getMetaInformation().getStartLine()
                    );
                }
            }
        }
    }

}
