package qlviz.model.question;

import qlviz.QLParser;
import qlviz.model.expressions.Expression;

public abstract class NumericQuestion extends Question {

    protected final Expression valueExpression;
    public Expression getValueExpression() {
        return valueExpression;
    }

    public NumericQuestion(String name, String text, QuestionType type, Expression valueExpression, QLParser.QuestionContext context) {
        super(name, text, type, context);
        this.valueExpression = valueExpression;
    }


}

