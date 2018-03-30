package qlviz.model.question;

import qlviz.QLParser;
import qlviz.model.expressions.Expression;

public class BooleanQuestion extends Question {

    protected final Expression valueExpression;
    public Expression getValueExpression() {
        return this.valueExpression;
    }

    public BooleanQuestion(String name, String text, QuestionType type, Expression valueExpression, QLParser.QuestionContext questionContext) {
        super(name, text, type, questionContext);
        this.valueExpression = valueExpression;
    }

    @Override
    public void accept(VoidQuestionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(QuestionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

