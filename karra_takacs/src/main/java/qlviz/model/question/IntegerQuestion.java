package qlviz.model.question;

import qlviz.model.NumericQuestion;

public class IntegerQuestion extends NumericQuestion {

    public IntegerQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }

    @Override
    public void accept(QuestionVisitor visitor) {
        visitor.visit(this);
    }
}

