package qlviz.model.question;

import qlviz.QLParser;

import java.util.Date;

public class DateQuestion extends Question {
    private Date value;

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public DateQuestion(String name, String text, QuestionType type, QLParser.QuestionContext context) {
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

