package qlviz.model.question;

import qlviz.QLParser;

public class StringQuestion extends Question {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringQuestion(String name, String text, QuestionType type, QLParser.QuestionContext context) {
        super(name, text, type, context);
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

