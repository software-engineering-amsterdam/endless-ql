package ql.logic.validators;

import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.Question;
import ql.exceptions.IllegalOperationOnTypesException;
import ql.exceptions.IncompatibleTypesException;
import ql.logic.evaluators.ASTExpressionEvaluator;

import java.util.ArrayList;
import java.util.List;

public final class TypesValidator {

    public static void validateTypes(List<Expression> conditions, List<Question> questions) {

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
            } catch (IllegalOperationOnTypesException e) {
                throw new IllegalOperationOnTypesException(e.getMessage() + ". Line " + expression.getMetaInformation().getStartLine() + ".");
            } catch (IncompatibleTypesException e) {
                throw new IncompatibleTypesException(e.getMessage() + ". Line " + expression.getMetaInformation().getStartLine() + ".");
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage() + ". Line " + expression.getMetaInformation().getStartLine() + ".");
            }
        }
    }

}
