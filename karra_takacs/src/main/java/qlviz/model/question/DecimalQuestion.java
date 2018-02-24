package qlviz.model.question;

import qlviz.model.NumericQuestion;

public class DecimalQuestion extends NumericQuestion {

    public DecimalQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }

    @Override
    public void accept(QuestionVisitor visitor) {
        visitor.visit(this);
    }
}
