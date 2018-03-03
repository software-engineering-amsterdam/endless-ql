package qlviz.model.question;

import qlviz.QLParser;

public class BooleanQuestion extends Question {
    private boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public BooleanQuestion(String name, String text, QuestionType type, QLParser.QuestionContext questionContext) {
        super(name, text, type, questionContext);
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

