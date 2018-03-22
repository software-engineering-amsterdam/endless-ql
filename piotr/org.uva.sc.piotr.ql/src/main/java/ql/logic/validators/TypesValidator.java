package ql.logic.validators;

import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.logic.evaluators.ASTExpressionEvaluator;

import java.util.ArrayList;
import java.util.List;

public final class TypesValidator extends Validator {

    private final List<Expression> conditions;
    private final List<Question> questions;

    public TypesValidator(List<Expression> conditions, List<Question> questions) {
        this.conditions = conditions;
        this.questions = questions;
    }

    public boolean validate() {

        ASTExpressionEvaluator evaluator = new ASTExpressionEvaluator(questions);

        List<Expression> expressions = new ArrayList<>(conditions);

        // add assigned expressions
        for (Question question : questions) {
            if (question.getAssignedExpression() != null) {
                expressions.add(question.getAssignedExpression());
            }
        }

        for (Expression expression : expressions) {
            try {
                expression.accept(evaluator);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                String message  = e.getMessage() + ". Line " + expression.getMetaInformation().getStartLine() + ".";
                this.setError(new Error(Error.Level.CRITICAL, message));
                return false;
            }
        }

        return true;
    }
}
