package logic.validators;

import ast.model.expressions.Expression;
import ast.model.statements.Question;
import exceptions.NonBooleanConditionException;
import logic.evaluators.ASTExpressionEvaluator;
import logic.type.MixedValue;

import java.util.List;

public final class ConditionsValidator {

    public static void validateConditions(List<Expression> conditions, List<Question> questions) throws NonBooleanConditionException {

        ASTExpressionEvaluator evaluator = new ASTExpressionEvaluator(questions);

        for (Expression expression : conditions) {
            MixedValue result = expression.accept(evaluator);
            if (result.getType() != Expression.DataType.BOOLEAN) {
                throw new NonBooleanConditionException("Condition at line " + expression.getMetaInformation().getStartLine() + " does not evaluate to a boolean type.");
            }
        }
    }

}
