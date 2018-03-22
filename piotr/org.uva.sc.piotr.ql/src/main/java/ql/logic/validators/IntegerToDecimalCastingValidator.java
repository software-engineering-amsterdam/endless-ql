package ql.logic.validators;

import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.logic.repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class IntegerToDecimalCastingValidator extends Validator {

    private final HashMap<Question, List<VariableReference>> questionsMap;
    private final List<Question> questions;

    public IntegerToDecimalCastingValidator(HashMap<Question, List<VariableReference>> questionsMap) {
        this.questionsMap = questionsMap;
        this.questions = new ArrayList<>(questionsMap.keySet());
    }

    public boolean validate() {

        for (Map.Entry<Question, List<VariableReference>> entry : this.questionsMap.entrySet()) {
            for (VariableReference reference : entry.getValue()) {
                // if integer refers to a decimal -> failed

                Question refQuestion = QuestionRepository
                        .findQuestionByVariableName(reference.getName(), this.questions);
                if (
                        entry
                                .getKey()
                                .getVariableType()
                                .toDataType()
                                .equals(Expression.DataType.INTEGER)
                                &&
                                refQuestion
                                        .getVariableType()
                                        .toDataType()
                                        .equals(Expression.DataType.DECIMAL)
                        ) {
                    String message = "Illegal assignment: Assignment to variable \"" + entry.getKey().getVariableName() + "\" of type " + entry.getKey().getVariableType().toDataType() + " in line " + entry.getKey().getMetaInformation().getStartLine() + " can not take as argument variable \"" + refQuestion.getVariableName() + "\" of type " + refQuestion.getVariableType().toDataType() + ".";
                    this.setError(new Error(Error.Level.CRITICAL, message));
                    return false;
                }
            }
        }

        return true;
    }
}
