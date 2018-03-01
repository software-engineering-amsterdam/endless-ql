package domain.model.expression;

import domain.model.Condition;
import domain.model.question.QuestionVariable;

public class BooleanExpression extends Condition {

    private QuestionVariable leftHandOperator;
    private QuestionVariable rightHandOperator;
    private String operator;

    public BooleanExpression(QuestionVariable leftHandOperator, QuestionVariable rightHandOperator, String operator) {
        this.leftHandOperator = leftHandOperator;
        this.rightHandOperator = rightHandOperator;
        this.operator = operator;
    }
}
