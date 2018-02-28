package qlviz.model.question;

import java.util.Date;

public class DateQuestion extends Question {
    private Date value;

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public DateQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
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

