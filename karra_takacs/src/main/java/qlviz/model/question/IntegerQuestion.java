package qlviz.model.question;

import qlviz.QLParser;
import qlviz.model.expressions.Expression;

public class IntegerQuestion extends NumericQuestion {

    public IntegerQuestion(String name, String text, QuestionType type, Expression valueExpression, QLParser.QuestionContext context) {
        super(name, text, type, valueExpression, context);
    }

    @Override
    public <T> T accept(QuestionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidQuestionVisitor visitor) {
        visitor.visit(this);
    }
}

