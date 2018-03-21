package ql.logic.validators;

import ql.ast.model.statements.Question;
import ql.exceptions.DuplicateDeclarationException;
import ql.exceptions.DuplicateLabelException;

import java.util.List;
import java.util.Objects;

public final class QuestionsValidator {

    public static void validateDuplicates(List<Question> questions) throws DuplicateDeclarationException {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getVariableName(), question2.getVariableName())
                        && !Objects.equals(question1.getVariableType().getIdentifier(), question2.getVariableType().getIdentifier())
                        ) {
                    throw new DuplicateDeclarationException("Duplicate question declarations with different types in lines : "
                            + question1.getMetaInformation().getStartLine()
                            + " and "
                            + question2.getMetaInformation().getStartLine()
                    );
                }
            }
        }
    }

    public static  void validateLabels(List<Question> questions) throws DuplicateLabelException {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getLabel(), question2.getLabel())) {
                    throw new DuplicateLabelException("Duplicate label declarations in lines : "
                            + question1.getMetaInformation().getStartLine()
                            + " and "
                            + question2.getMetaInformation().getStartLine()
                    );
                }
            }
        }
    }

}
