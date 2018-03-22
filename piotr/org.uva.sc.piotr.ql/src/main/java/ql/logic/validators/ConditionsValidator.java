package ql.logic.validators;

import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.logic.evaluators.ASTExpressionEvaluator;
import ql.logic.type.QLDataType;

import java.util.List;

public final class ConditionsValidator extends Validator {

    private final List<Expression> conditions;
    private final List<Question> questions;

    public ConditionsValidator(List<Expression> conditions, List<Question> questions) {
        this.conditions = conditions;
        this.questions = questions;
    }

    @Override
    public boolean validate() {

        ASTExpressionEvaluator evaluator = new ASTExpressionEvaluator(questions);

        for (Expression expression : conditions) {
            QLDataType result = expression.accept(evaluator);
            if (result.getType() != Expression.DataType.BOOLEAN) {
                String message = "Condition at line " + expression.getMetaInformation().getStartLine() + " does not evaluate to a boolean type.";
                this.setError(new Error(Error.Level.CRITICAL, message));
                return false;
            }
        }

        return true;
    }

}
