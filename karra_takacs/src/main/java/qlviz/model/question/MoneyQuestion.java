package qlviz.model.question;

import qlviz.QLParser;
import qlviz.model.numericExpressions.NumericExpression;

public class MoneyQuestion extends NumericQuestion {

   public MoneyQuestion(String name, String text, QuestionType type, NumericExpression valueExpression, QLParser.QuestionContext context) {
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

