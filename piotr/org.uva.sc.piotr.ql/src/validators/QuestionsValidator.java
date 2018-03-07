package validators;

import ast.model.statements.Question;

import java.util.ArrayList;
import java.util.Objects;

public final class QuestionsValidator {

    public static void validateDuplicates(ArrayList<Question> questions) throws RuntimeException {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getVariableName(), question2.getVariableName())
                        && !Objects.equals(question1.getVariableType().getIdentifier(), question2.getVariableType().getIdentifier())
                        ) {
                    throw new RuntimeException("Duplicate question declarations with different types in lines : "
                            + question1.getStartLine()
                            + "-"
                            + question1.getEndLine()
                            + " and "
                            + question2.getStartLine()
                            + "-"
                            + question2.getEndLine()
                    );
                }
            }
        }
    }

    public static  void validateLabels(ArrayList<Question> questions) throws Exception {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getLabel(), question2.getLabel())) {
                    throw new Exception("Duplicate label declarations in lines : "
                            + question1.getStartLine()
                            + " and "
                            + question2.getStartLine()
                    );
                }
            }
        }
    }

}
